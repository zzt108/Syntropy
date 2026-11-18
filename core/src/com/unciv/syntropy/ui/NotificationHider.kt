package com.unciv.syntropy.ui

import com.unciv.GUI
import com.unciv.logic.civilization.Notification
import java.util.Collections
import java.util.IdentityHashMap

/**
 * SYNTROPY: lets the player hide single on-screen notifications (right click) and restore
 * them per category (right click on the category header).
 *
 * All state and logic live here so that upstream files only carry one-line hook points
 * (marked `// SYNTROPY-HOOK`):
 * - `WorldScreen`: the list passed to `NotificationsScroll.update` goes through [visible];
 * - `NotificationsScroll.ListItem`: right click calls [hide];
 * - `NotificationsScroll` category header: right click calls [restore].
 *
 * Notifications are tracked by **identity** (`===`), matching upstream, where [Notification]
 * has no equality contract. Two distinct notifications with the same text are hidden separately.
 *
 * Render thread only: not synchronised.
 */
object NotificationHider {

    private val hidden: MutableSet<Notification> = Collections.newSetFromMap(IdentityHashMap())

    /** Called whenever the hidden set changes; replaceable in tests. */
    var onChanged: () -> Unit = { GUI.setUpdateWorldOnNextRender() }

    /**
     * Drops hidden entries that are no longer in [notifications] (new turn, new game, other civ),
     * then returns [notifications] without the hidden ones.
     * Returns the same list instance when nothing is hidden.
     */
    fun visible(notifications: List<Notification>): List<Notification> {
        if (hidden.isEmpty()) return notifications
        val current: MutableSet<Notification> = Collections.newSetFromMap(IdentityHashMap())
        current.addAll(notifications)
        hidden.retainAll(current)
        if (hidden.isEmpty()) return notifications
        return notifications.filter { it !in hidden }
    }

    /** Hides [notification] from the on-screen list. */
    fun hide(notification: Notification) {
        if (hidden.add(notification)) onChanged()
    }

    /** Restores all hidden notifications of [category]; returns true if any were restored. */
    fun restore(category: Notification.NotificationCategory): Boolean {
        val restored = hidden.removeIf { it.category == category }
        if (restored) onChanged()
        return restored
    }

    /** Restores every hidden notification. */
    fun restoreAll() {
        if (hidden.isEmpty()) return
        hidden.clear()
        onChanged()
    }
}
