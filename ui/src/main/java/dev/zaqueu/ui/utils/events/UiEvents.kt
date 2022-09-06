package dev.zaqueu.ui.utils.events

sealed class UiEvents {
    data class Navigate(val route: String): UiEvents()
    object Pop : UiEvents()
}
