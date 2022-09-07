package dev.zaqueu.moviefinder.data.remote.mappers

import dev.zaqueu.moviefinder.data.remote.dtos.EpisodeDto
import dev.zaqueu.moviefinder.domain.models.Episode

fun List<EpisodeDto>.mapToEpisode(): List<Episode> {
    return this.map { it.mapToEpisode() }
}

fun EpisodeDto.mapToEpisode(): Episode {
    return Episode(
        id = id,
        name = name,
        number = number,
        season = season,
        summary = summary.orEmpty(),
        image = image?.medium
    )
}
