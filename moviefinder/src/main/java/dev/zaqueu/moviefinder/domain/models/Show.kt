package dev.zaqueu.moviefinder.domain.models

data class Show(
    val id: Int,
    val name: String,
    val cover: String,
    val rating: String,
    val summary: String,
    val isFavourite: Boolean
)

data class Shows(
    val shows: List<Show>
)
