package com.unciv.testing.syntropy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import java.io.File

/**
 * Guards the SYNTROPY hook points in upstream Unciv files.
 *
 * The fork is never upstreamed (D-43), so our code reaches into upstream files only through
 * one-line hook points marked with a trailing `// SYNTROPY-HOOK` comment. An upstream merge
 * resolved with "theirs" would drop such a line silently while the build stays green.
 * This test reads the sources (not bytecode) and checks the hook lines against [hooks]:
 * every registered hook must be present, and every hook line must be registered.
 *
 * Adding a hook: put the marker at the end of the line and add an entry to [hooks].
 */
class SyntropyHookInventoryTest {

    /** @param file path relative to `core/src`; @param expectedCall distinctive part of the hook line */
    private data class Hook(val id: String, val file: String, val expectedCall: String)

    private val hooks = listOf(
        Hook("H0", "com/unciv/ui/screens/overviewscreen/EmpireOverviewScreen.kt", "aiexport.createAiExportHeader("),
        Hook("H1", "com/unciv/ui/screens/worldscreen/WorldScreen.kt", "NotificationHider.visible("),
        Hook("H2", "com/unciv/ui/screens/worldscreen/NotificationsScroll.kt", "NotificationHooks.listItem("),
        Hook("H3", "com/unciv/ui/screens/worldscreen/NotificationsScroll.kt", "NotificationHooks.categoryHeader("),
    )

    private data class HookLine(val file: String, val lineNumber: Int, val text: String)

    private val marker = "// SYNTROPY-HOOK"

    /** Tests run with working dir `android/assets`; walk up until the repo root is found. */
    private val coreSrc: File by lazy {
        var dir: File? = File(System.getProperty("user.dir")).absoluteFile
        while (dir != null) {
            val candidate = File(dir, "core/src")
            if (File(candidate, "com/unciv").isDirectory) return@lazy candidate
            dir = dir.parentFile
        }
        fail("Could not find core/src/com/unciv above user.dir=${System.getProperty("user.dir")}")
        throw IllegalStateException()  // unreachable, fail() throws
    }

    private fun isHookLine(line: String) = line.trimEnd().endsWith(marker)

    private fun hookLinesIn(file: File): List<HookLine> {
        val relative = file.relativeTo(coreSrc).invariantSeparatorsPath
        return file.readLines().mapIndexedNotNull { index, line ->
            if (isHookLine(line)) HookLine(relative, index + 1, line.trim()) else null
        }
    }

    private val allHookLines: List<HookLine> by lazy {
        coreSrc.walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .flatMap { hookLinesIn(it) }
            .toList()
    }

    private fun Hook.matches(line: HookLine) = line.file == file && expectedCall in line.text

    @Test
    fun everyRegisteredHookIsPresentExactlyOnce() {
        for (hook in hooks) {
            val file = File(coreSrc, hook.file)
            assertTrue("${hook.id}: file ${hook.file} not found - renamed or moved upstream?", file.isFile)
            val found = hookLinesIn(file).filter { hook.matches(it) }
            assertEquals(
                "${hook.id}: expected exactly one line in ${hook.file} containing '${hook.expectedCall}' " +
                    "and ending with '$marker' - lost in an upstream merge? Found: $found",
                1, found.size
            )
        }
    }

    @Test
    fun noUnregisteredHookLines() {
        val unregistered = allHookLines.filter { line -> hooks.none { it.matches(line) } }
        assertTrue(
            "Hook lines not registered in SyntropyHookInventoryTest.hooks:\n" +
                unregistered.joinToString("\n") { "  ${it.file}:${it.lineNumber}  ${it.text}" },
            unregistered.isEmpty()
        )
    }

    @Test
    fun registryIsConsistent() {
        assertEquals("Hook ids must be unique", hooks.size, hooks.map { it.id }.toSet().size)
        assertEquals(
            "Number of '$marker' lines under core/src differs from the registry",
            hooks.size, allHookLines.size
        )
    }
}
