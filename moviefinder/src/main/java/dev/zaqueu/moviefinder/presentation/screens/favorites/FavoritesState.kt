package dev.zaqueu.moviefinder.presentation.screens.favorites

import dev.zaqueu.moviefinder.domain.models.Show

data class FavoritesState(
    val shows: List<Show> = emptyList(),
    val isLoading: Boolean = true
)
