package dev.zaqueu.moviefinder.data.remote.dtos

data class ShowDto(
    val id: Int,
    val name: String,
    val genres: List<String>,
    val rating: Rating,
    val summary: String,
    val image: Image
) {
    data class Rating(
        val average: String?
    )

    data class Image(
        val medium: String
    )
}

data class ShowsDto(
    val shows: List<ShowDto>
)
