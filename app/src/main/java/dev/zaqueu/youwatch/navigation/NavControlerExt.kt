package dev.zaqueu.youwatch.navigation

import androidx.navigation.NavController
import dev.zaqueu.ui.utils.events.UiEvents

fun NavController.navigate(event: UiEvents) {
    when(event) {
        is UiEvents.Navigate -> this.navigate(event.route)
        is UiEvents.Pop -> this.popBackStack()
    }
}
