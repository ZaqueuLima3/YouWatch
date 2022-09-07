package dev.zaqueu.ui.utils.events

import dev.zaqueu.ui.utils.text.TextResource

sealed class UiEvents {
    data class Navigate(val route: String) : UiEvents()
    object Pop : UiEvents()
    data class ShowSnackBar(val message: TextResource) : UiEvents()
}
