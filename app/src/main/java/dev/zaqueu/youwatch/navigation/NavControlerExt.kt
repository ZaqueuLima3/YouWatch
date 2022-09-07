package dev.zaqueu.youwatch.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDestination
import dev.zaqueu.core.navigation.NavRoutes
import dev.zaqueu.ui.utils.events.UiEvents

fun NavController.navigate(event: UiEvents) {
    when (event) {
        is UiEvents.Navigate -> {
            this.navigate(event.route) {
                launchSingleTop = true
            }
        }
        is UiEvents.NavigateAndClean -> {
            this.navigate(event.toRoute) {
                popUpTo(event.fromRoute) { inclusive = true }
            }
        }
        is UiEvents.Pop -> this.popBackStack()
        else -> {}
    }
}
