package dev.zaqueu.moviefinder.data.remote.mappers

import dev.zaqueu.moviefinder.data.remote.dtos.ShowDto
import dev.zaqueu.moviefinder.domain.models.Show
import java.time.LocalDate

fun List<ShowDto>.mapToShows(): List<Show> {
    return this.map { it.mapToModel() }
}

fun ShowDto.mapToModel(isFavorite: Boolean = false): Show {
    return Show(
        id = id,
        name = name,
        cover = image?.medium,
        rating = rating?.average,
        summary = summary,
        genres = genres,
        premiered = premiered?.let { LocalDate.parse(premiered) },
        ended = ended?.let { LocalDate.parse(ended) },
        isFavorite = isFavorite
    )
}
