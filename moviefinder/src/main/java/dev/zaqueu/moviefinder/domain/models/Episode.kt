package dev.zaqueu.moviefinder.domain.models

data class Episode(
    val id: Int,
    val name: String,
    val number: Int,
    val season: Int,
    val summary: String,
    val image: String?
)
