package dev.zaqueu.moviefinder.domain.usecases

import dev.zaqueu.moviefinder.domain.models.Show
import dev.zaqueu.moviefinder.domain.repositories.FavoritesRepository

class GetAllShowsFromFavorites(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(): List<Show> {
        return favoritesRepository.getAllShowsFromFavorites()
    }
}
