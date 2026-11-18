package com.unciv.syntropy.ui

import com.badlogic.gdx.scenes.scene2d.Actor
import com.unciv.logic.civilization.Notification
import com.unciv.ui.components.input.onRightClick

/**
 * SYNTROPY: entry points called from the one-line `// SYNTROPY-HOOK` lines in upstream
 * `NotificationsScroll`. They attach our listeners to upstream actors, so the upstream file
 * needs neither imports nor logic. Kotlin extension functions (like `onRightClick`) cannot be
 * called with a fully qualified name, hence this indirection.
 */
object NotificationHooks {

    /** Right click on a notification item hides it. */
    fun listItem(item: Actor, notification: Notification) {
        item.onRightClick { NotificationHider.hide(notification) }
    }

    /** Right click on a category header restores the hidden notifications of that category. */
    fun categoryHeader(header: Actor?, category: Notification.NotificationCategory) {
        header?.onRightClick { NotificationHider.restore(category) }
    }
}
