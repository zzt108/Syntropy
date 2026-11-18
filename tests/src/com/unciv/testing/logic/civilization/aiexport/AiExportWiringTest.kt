package com.unciv.testing.logic.civilization.aiexport

import com.unciv.ui.screens.overviewscreen.EmpireOverviewScreen
import com.unciv.ui.screens.overviewscreen.aiexport.createAiExportHeader
import com.unciv.view.CivView
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Guards the AI status export hook points in upstream Unciv code.
 *
 * The fork is never upstreamed, so every upstream merge may silently drop our hook
 * (e.g. a conflict resolved with "theirs"). These tests inspect the compiled bytecode
 * of the production classes, so they fail as soon as the call chain
 * EmpireOverviewScreen -> createAiExportHeader -> AiStatusExporter.generateAiStatusReport
 * is broken, without needing a running UI.
 */
class AiExportWiringTest {

    private val injectorClass = "com.unciv.ui.screens.overviewscreen.aiexport.AiExportUIInjectorKt"
    private val exporterClass = "com.unciv.logic.civilization.aiexport.AiStatusExporter"

    /** Returns the raw class file as Latin-1 text, so constant pool names can be searched as substrings. */
    private fun classFileText(className: String): String {
        val path = className.replace('.', '/') + ".class"
        val stream = javaClass.classLoader.getResourceAsStream(path)
        assertNotNull("Class file not found on classpath: $path", stream)
        return stream!!.use { String(it.readBytes(), Charsets.ISO_8859_1) }
    }

    private fun internalName(className: String) = className.replace('.', '/')

    @Test
    fun empireOverviewScreenUsesAiExportHeader() {
        val screen = classFileText(EmpireOverviewScreen::class.java.name)
        assertTrue(
            "EmpireOverviewScreen no longer references $injectorClass - was the hook lost in an upstream merge?",
            screen.contains(internalName(injectorClass))
        )
        assertTrue(
            "EmpireOverviewScreen no longer calls createAiExportHeader - was the hook lost in an upstream merge?",
            screen.contains("createAiExportHeader")
        )
    }

    @Test
    fun aiExportHeaderCallsStatusExporter() {
        val injector = classFileText(injectorClass)
        assertTrue(
            "createAiExportHeader no longer references $exporterClass",
            injector.contains(internalName(exporterClass))
        )
        assertTrue(
            "createAiExportHeader no longer calls generateAiStatusReport",
            injector.contains("generateAiStatusReport")
        )
    }

    @Test
    fun aiExportHeaderSignatureMatchesHook() {
        // Compile-time guard: the hook in EmpireOverviewScreen passes (CivView, Actor) and expects a Table.
        val hook: (CivView, com.badlogic.gdx.scenes.scene2d.Actor) -> com.badlogic.gdx.scenes.scene2d.ui.Table =
            ::createAiExportHeader
        assertNotNull(hook)
    }
}
