package dev.zaqueu.moviefinder.domain.usecases

import dev.zaqueu.moviefinder.domain.models.Episode
import dev.zaqueu.moviefinder.domain.repositories.ShowsRepository

class GetEpisode(
    private val showsRepository: ShowsRepository
) {
    suspend operator fun invoke(episodeId: String): Result<Episode> {
        return showsRepository.getEpisode(episodeId)
    }
}
