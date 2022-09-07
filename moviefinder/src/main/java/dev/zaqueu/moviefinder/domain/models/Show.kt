package dev.zaqueu.moviefinder.domain.models

import java.time.LocalDate

data class Show(
    val id: Long,
    val name: String,
    val cover: String?,
    val rating: Double?,
    val summary: String,
    val premiered: LocalDate?,
    val ended: LocalDate?,
    val genres: List<String>,
    val isFavorite: Boolean = false
)
