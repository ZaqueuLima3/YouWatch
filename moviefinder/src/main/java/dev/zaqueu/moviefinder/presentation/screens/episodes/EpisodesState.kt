package dev.zaqueu.moviefinder.presentation.screens.episodes

import dev.zaqueu.moviefinder.domain.models.Episode
import dev.zaqueu.moviefinder.domain.models.Show

data class EpisodesState(
    val episodes: Map<Int, List<Episode>> = emptyMap(),
    val isLoading: Boolean = true
)
