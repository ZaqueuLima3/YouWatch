package dev.zaqueu.moviefinder.presentation.screens.details

sealed class DetailsEvent {
    data class OnEnterScreen(val showId: String) : DetailsEvent()
    object OnBackClick : DetailsEvent()
}