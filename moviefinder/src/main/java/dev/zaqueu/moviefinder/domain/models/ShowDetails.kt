package dev.zaqueu.moviefinder.domain.models

data class ShowDetails(
    val id: Long,
    val name: String,
    val cover: String,
    val rating: Double,
    val summary: String,
    val premiered: String,
    val ended: String,
    val genres: List<String>
)
