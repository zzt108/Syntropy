package com.unciv.testing.logic.civilization.aiexport

import com.unciv.ui.screens.overviewscreen.aiexport.exportCivMatches
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AiExportCivGuardTest {

    @Test
    fun sameCivMatches() {
        assertTrue(exportCivMatches("Babylon", "Babylon"))
    }

    @Test
    fun differentCivDoesNotMatch() {
        assertFalse(exportCivMatches("Babylon", "Greece"))
    }

    @Test
    fun comparisonIsCaseSensitive() {
        // civName is an identifier, not display text
        assertFalse(exportCivMatches("Babylon", "babylon"))
    }
}
