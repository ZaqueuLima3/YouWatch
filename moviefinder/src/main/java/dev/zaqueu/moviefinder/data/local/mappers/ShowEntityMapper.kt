package dev.zaqueu.moviefinder.data.local.mappers

import dev.zaqueu.database.data.aggregate.ShowAggregate
import dev.zaqueu.database.data.entities.GenreEntity
import dev.zaqueu.database.data.entities.ShowEntity
import dev.zaqueu.moviefinder.domain.models.Show
import java.time.LocalDate

fun List<ShowAggregate>.mapToModel(): List<Show> {
    return this.map { it.mapToModel() }
}

fun ShowAggregate.mapToModel(): Show {
    return Show(
        id = show.id,
        name = show.name,
        cover = show.cover,
        rating = show.rating,
        summary = show.summary,
        genres = genres.map { it.name },
        premiered = show.premiered?.let { LocalDate.parse(show.premiered) },
        ended = show.ended?.let { LocalDate.parse(show.ended) },
        isFavorite = true,
    )
}

fun Show.mapToEntity(): ShowAggregate {
    val show = ShowEntity(
        id = id,
        name = name,
        cover = cover,
        rating = rating,
        summary = summary,
        premiered = premiered?.toString(),
        ended = ended?.toString()
    )
    val genres = genres.map { name ->
        GenreEntity(
            showId = id,
            name = name
        )
    }
    return ShowAggregate(
        show = show,
        genres = genres
    )
}
