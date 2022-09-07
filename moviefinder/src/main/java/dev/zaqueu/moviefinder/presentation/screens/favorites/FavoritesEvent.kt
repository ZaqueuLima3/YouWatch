package dev.zaqueu.moviefinder.presentation.screens.favorites

import dev.zaqueu.moviefinder.domain.models.Show

sealed class FavoritesEvent {
    object OnEnterScreen : FavoritesEvent()
    data class OnItemClick(val show: Show) : FavoritesEvent()
    data class OnFavoriteClick(val show: Show) : FavoritesEvent()
}
