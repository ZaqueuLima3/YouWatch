package dev.zaqueu.moviefinder.data.remote.mappers

import dev.zaqueu.moviefinder.data.remote.dtos.ShowDto
import dev.zaqueu.moviefinder.domain.models.Show

fun List<ShowDto>.mapToShows(): List<Show> {
    return this.map { it.mapToModel() }
}

fun ShowDto.mapToModel(): Show {
    return Show(
        id = id,
        name = name,
        cover = image?.medium,
        rating = rating?.average,
        summary = summary,
        isFavourite = false
    )
}
