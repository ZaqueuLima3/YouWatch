package dev.zaqueu.moviefinder.data.remote.services

import dev.zaqueu.moviefinder.data.remote.dtos.ShowsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TvMazeApi {
    @GET("shows?page=page")
    suspend fun getAllMovies(
        @Query("page") page: Int,
    ): ShowsDto

    @GET("search/shows?q=name")
    suspend fun searchShow(
        @Query("name") name: String
    ): ShowsDto

    companion object {
        const val BASE_URL = "https://api.tvmaze.com/"
    }
}
