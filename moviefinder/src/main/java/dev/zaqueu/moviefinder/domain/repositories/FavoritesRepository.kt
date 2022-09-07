package dev.zaqueu.moviefinder.domain.repositories

import dev.zaqueu.moviefinder.domain.models.Show

interface FavoritesRepository {
    suspend fun saveShowAsFavorite(show: Show)

    suspend fun getAllShowsFromFavorites(): List<Show>

    suspend fun removeShowFromFavorites(show: Show)
}
