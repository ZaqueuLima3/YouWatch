package dev.zaqueu.moviefinder.data.remote.mappers

import dev.zaqueu.moviefinder.data.remote.dtos.SearchShowDto
import dev.zaqueu.moviefinder.domain.models.Show


fun List<SearchShowDto>.mapToShows(): List<Show> {
    return this.map { it.show.mapToModel() }
}
