package dev.zaqueu.moviefinder.domain.usecases

import dev.zaqueu.moviefinder.domain.models.Episode
import dev.zaqueu.moviefinder.domain.repositories.ShowsRepository

class GetEpisodes(
    private val showsRepository: ShowsRepository
) {
    suspend operator fun invoke(showId: String): Result<Map<Int, List<Episode>>> {
        return showsRepository.getShowEpisodes(showId)
    }
}
