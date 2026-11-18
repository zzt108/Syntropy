package com.unciv.ui.screens.overviewscreen.aiexport

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.unciv.GUI
import com.unciv.logic.civilization.aiexport.AiStatusExporter
import com.unciv.ui.components.UncivTooltip.Companion.addTooltip
import com.unciv.ui.components.extensions.toCheckBox
import com.unciv.ui.components.extensions.toTextButton
import com.unciv.ui.components.input.onClick
import com.unciv.ui.popups.ToastPopup
import com.unciv.view.CivView

/**
 * Creates the header table for the Empire Overview screen, injecting the AI Status Export tools.
 * Gracefully degrades to just returning a table with the close button if the export feature fails to load.
 *
 * [viewingPlayer] is the overview's [CivView]; upstream keeps its wrapped Civilization protected.
 * The exporter needs the full Civilization, so it is taken from the world screen's selected civ,
 * which is the civ the overview was built from (`WorldScreen.selectedGameView.civView`).
 * [viewingPlayer] guards that assumption: if its civ differs from the selected civ, nothing is
 * exported (see [exportCivMatches]).
 */
fun createAiExportHeader(viewingPlayer: CivView, closeButton: Actor): Table {
    val headerTable = Table()

    try {
        val includeContextCheckbox = "Context".toCheckBox(AiExportPreferences.includeContext) {
            AiExportPreferences.includeContext = it
        }
        includeContextCheckbox.addTooltip("Check to include the Unciv ruleset and map instructions (Recommended for new AI chats)")

        val exportButton = "Copy Status".toTextButton()
        exportButton.onClick {
            val stage = includeContextCheckbox.stage
            val selectedCiv = GUI.getSelectedPlayer()
            if (!exportCivMatches(viewingPlayer.civName, selectedCiv.civName)) {
                Gdx.app.error(
                    "AiExportUIInjector",
                    "Overview shows '${viewingPlayer.civName}' but the selected civ is '${selectedCiv.civName}' - export skipped"
                )
                if (stage != null) ToastPopup("Status export unavailable for this civilization", stage)
                return@onClick
            }
            val reportText = AiStatusExporter.generateAiStatusReport(selectedCiv, includeContextCheckbox.isChecked)
            Gdx.app.clipboard.contents = reportText
            if (stage != null) {
                ToastPopup("Status for AI copied to clipboard!", stage)
            }
        }

        headerTable.add(includeContextCheckbox).padRight(10f)
        headerTable.add(exportButton).padRight(10f)
    } catch (e: Exception) {
        Gdx.app.error("AiExportUIInjector", "Failed to inject AI Export buttons", e)
    }

    headerTable.add(closeButton)
    return headerTable
}

/** True when the overview's civ is the one whose full Civilization the exporter would read. */
fun exportCivMatches(viewedCivName: String, selectedCivName: String): Boolean =
    viewedCivName == selectedCivName
