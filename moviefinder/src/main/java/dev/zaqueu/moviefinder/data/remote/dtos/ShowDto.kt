package dev.zaqueu.moviefinder.data.remote.dtos

data class ShowDto (
    val id: Long?,
    val name: String?,
    val genres: List<String> = emptyList(),
    val rating: Rating?,
    val image: Image?,
    val summary: String?,
) {
    data class Image (
        val medium: String?,
    )

    data class Rating (
        val average: Double?
    )
}
