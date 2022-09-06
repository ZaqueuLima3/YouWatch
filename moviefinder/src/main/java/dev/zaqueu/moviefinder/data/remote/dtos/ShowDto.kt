package dev.zaqueu.moviefinder.data.remote.dtos

data class ShowDto (
    val id: Long,
    val name: String,
    val genres: List<String> = emptyList(),
    val rating: Rating?,
    val image: Image?,
    val summary: String,
    val premiered: String?,
    val ended: String?
) {
    data class Rating (
        val average: Double?
    )
}
