package dev.zaqueu.moviefinder.fixtures

import dev.zaqueu.moviefinder.data.remote.dtos.ShowDto
import dev.zaqueu.moviefinder.data.remote.dtos.ShowDto.Image
import dev.zaqueu.moviefinder.data.remote.dtos.ShowDto.Rating
import dev.zaqueu.moviefinder.data.remote.dtos.ShowsDto

fun createShowDto(
    id: Int = 1,
    name: String = "Shows name",
    genres: List<String> = listOf("Comedy"),
    rating: String = "6.5",
    summary: String = "summary",
    image: String = "image-url"
): ShowDto {
    return ShowDto(
        id = id,
        name = name,
        genres = genres,
        rating = Rating(average = rating),
        summary = summary,
        image = Image(medium = image)
    )
}

fun createShowsDto(shows: List<ShowDto>): ShowsDto {
    return ShowsDto(
        shows = shows
    )
}
