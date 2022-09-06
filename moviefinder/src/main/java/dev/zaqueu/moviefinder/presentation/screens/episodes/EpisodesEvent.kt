package dev.zaqueu.moviefinder.presentation.screens.episodes

sealed class EpisodesEvent {
    data class OnEnterScreen(val showId: String) : EpisodesEvent()
    data class OnEpisodesClick(val episodeId: Int) : EpisodesEvent()
    object OnBackClick : EpisodesEvent()
}
