package dev.zaqueu.moviefinder.data.remote.dtos

data class EpisodeDto(
    val id: Int,
    val name: String,
    val number: Int,
    val season: Int,
    val summary: String?,
    val image: Image?
)
