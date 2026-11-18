package com.unciv.testing.syntropy

import com.unciv.logic.civilization.Notification
import com.unciv.logic.civilization.NotificationCategory
import com.unciv.syntropy.ui.NotificationHider
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class NotificationHiderTest {

    private var changedCount = 0

    private fun notification(text: String, category: NotificationCategory = NotificationCategory.General) =
        Notification(text, arrayOf(), null, category)

    @Before
    fun setUp() {
        NotificationHider.onChanged = {}
        NotificationHider.restoreAll()
        changedCount = 0
        NotificationHider.onChanged = { changedCount++ }
    }

    @After
    fun tearDown() {
        NotificationHider.onChanged = {}
        NotificationHider.restoreAll()
    }

    @Test
    fun visibleWithNothingHiddenReturnsSameInstance() {
        val list = listOf(notification("a"), notification("b"))
        assertSame(list, NotificationHider.visible(list))
        assertEquals(0, changedCount)
    }

    @Test
    fun hideRemovesNotificationFromVisibleList() {
        val a = notification("a")
        val b = notification("b")
        NotificationHider.hide(a)
        assertEquals(listOf(b), NotificationHider.visible(listOf(a, b)))
        assertEquals(1, changedCount)
    }

    @Test
    fun staleHiddenEntriesArePurged() {
        val a = notification("a")
        val b = notification("b")
        NotificationHider.hide(a)
        NotificationHider.visible(listOf(b))  // a is gone, e.g. new turn
        val list = listOf(a, b)
        assertSame(list, NotificationHider.visible(list))
    }

    @Test
    fun restoreOnlyAffectsGivenCategory() {
        val war = notification("war", NotificationCategory.War)
        val trade = notification("trade", NotificationCategory.Trade)
        NotificationHider.hide(war)
        NotificationHider.hide(trade)
        changedCount = 0

        assertTrue(NotificationHider.restore(NotificationCategory.War))
        assertEquals(1, changedCount)
        assertEquals(listOf(war), NotificationHider.visible(listOf(war, trade)))

        assertFalse(NotificationHider.restore(NotificationCategory.War))
        assertEquals(1, changedCount)
    }

    @Test
    fun hidingUsesIdentityNotText() {
        val first = notification("same")
        val second = notification("same")
        NotificationHider.hide(first)
        assertEquals(1, NotificationHider.visible(listOf(first, second)).size)
        assertSame(second, NotificationHider.visible(listOf(first, second)).single())
    }

    @Test
    fun restoreAllClearsAndNotifiesOnlyWhenSomethingWasHidden() {
        NotificationHider.restoreAll()
        assertEquals(0, changedCount)

        val a = notification("a")
        NotificationHider.hide(a)
        changedCount = 0
        NotificationHider.restoreAll()
        assertEquals(1, changedCount)
        val list = listOf(a)
        assertSame(list, NotificationHider.visible(list))
    }
}
