package dev.zaqueu.moviefinder.domain.usecases

import dev.zaqueu.moviefinder.domain.models.Show
import dev.zaqueu.moviefinder.domain.repositories.FavoritesRepository

class SaveShowAsFavorite(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(show: Show) {
        favoritesRepository.saveShowAsFavorite(show)
    }
}
