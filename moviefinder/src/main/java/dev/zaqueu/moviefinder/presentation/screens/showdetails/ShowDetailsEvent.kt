package dev.zaqueu.moviefinder.presentation.screens.showdetails

sealed class ShowDetailsEvent {
    data class OnEnterScreen(val showId: String) : ShowDetailsEvent()
    data class OnSeeEpisodesClick(val showId: String) : ShowDetailsEvent()
    object OnBackClick : ShowDetailsEvent()
}
