package dev.zaqueu.moviefinder.presentation.screens.episodedetails

import dev.zaqueu.moviefinder.domain.models.Episode

data class EpisodeDetailsState(
    val episode: Episode? = null,
    val isLoading: Boolean = true
)
