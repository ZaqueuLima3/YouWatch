package dev.zaqueu.moviefinder.fixtures

import dev.zaqueu.moviefinder.data.remote.dtos.SearchShowDto
import dev.zaqueu.moviefinder.data.remote.dtos.ShowDto
import dev.zaqueu.moviefinder.data.remote.dtos.ShowDto.Image
import dev.zaqueu.moviefinder.data.remote.dtos.ShowDto.Rating

fun createShowDto(
    id: Long = 1,
    name: String = "Shows name",
    genres: List<String> = listOf("Comedy"),
    rating: Double = 6.5,
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

fun createSearchShowsDto(show: ShowDto): SearchShowDto {
    return SearchShowDto(
        show = show
    )
}
