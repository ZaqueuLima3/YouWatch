package dev.zaqueu.moviefinder.domain.usecases

import dev.zaqueu.moviefinder.domain.models.Show
import dev.zaqueu.moviefinder.domain.repositories.ShowsRepository

class GetShow(
    private val showsRepository: ShowsRepository
) {
    suspend operator fun invoke(showId: String): Result<Show> {
        return showsRepository.getShow(showId)
    }
}

