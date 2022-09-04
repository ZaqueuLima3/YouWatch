package dev.zaqueu.moviefinder.data.remote.mappers

import dev.zaqueu.moviefinder.data.remote.dtos.ShowsDto
import dev.zaqueu.moviefinder.domain.models.Show

fun ShowsDto.mapToShows(): List<Show> {
    return this.shows.map {
        Show(
            id = it.id,
            name = it.name,
            cover = it.image.medium,
            rating = it.rating.average ?: "0.0",
            summary = it.summary,
            isFavourite = false
        )
    }
}
