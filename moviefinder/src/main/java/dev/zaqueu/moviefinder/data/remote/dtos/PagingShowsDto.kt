package dev.zaqueu.moviefinder.data.remote.dtos

import dev.zaqueu.moviefinder.domain.models.Show

data class PagingShowsDto(
    val shows: List<Show>,
    val isPaginated: Boolean
)
