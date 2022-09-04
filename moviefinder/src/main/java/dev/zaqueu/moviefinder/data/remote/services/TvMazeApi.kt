package dev.zaqueu.moviefinder.data.remote.services

import dev.zaqueu.moviefinder.data.remote.dtos.SearchShowDto
import dev.zaqueu.moviefinder.data.remote.dtos.ShowDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TvMazeApi {
    @GET("shows?page=page")
    suspend fun getAllMovies(
        @Query("page") page: Int,
    ): List<ShowDto>

    @GET("search/shows?q=name")
    suspend fun searchShow(
        @Query("name") name: String
    ): List<SearchShowDto>

    companion object {
        const val BASE_URL = "https://api.tvmaze.com/"
    }
}
