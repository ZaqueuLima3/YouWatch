package dev.zaqueu.moviefinder.domain.models

import dev.zaqueu.moviefinder.data.remote.dtos.Image

data class Episode(
    val id: Int,
    val name: String,
    val number: Int,
    val season: Int,
    val summary: String,
    val image: Image?
)
