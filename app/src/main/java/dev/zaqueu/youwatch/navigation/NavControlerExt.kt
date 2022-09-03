package dev.zaqueu.youwatch.navigation

import androidx.navigation.NavController
import dev.zaqueu.ui.utils.events.UiEvents

fun NavController.navigate(event: UiEvents.Navigate) {
    this.navigate(event.route)
}
