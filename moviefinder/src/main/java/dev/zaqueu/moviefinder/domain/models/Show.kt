package dev.zaqueu.moviefinder.domain.models

data class Show(
    val id: Long?,
    val name: String?,
    val cover: String?,
    val rating: Double?,
    val summary: String?,
    val isFavourite: Boolean
)

