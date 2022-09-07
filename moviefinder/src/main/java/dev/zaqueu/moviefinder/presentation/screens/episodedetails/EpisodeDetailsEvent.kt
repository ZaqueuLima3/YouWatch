package dev.zaqueu.moviefinder.presentation.screens.episodedetails

sealed class EpisodeDetailsEvent {
    data class OnEnterScreen(val showId: String) : EpisodeDetailsEvent()
    object OnBackClick : EpisodeDetailsEvent()
}
