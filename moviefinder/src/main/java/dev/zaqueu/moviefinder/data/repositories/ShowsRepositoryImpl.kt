package dev.zaqueu.moviefinder.data.repositories

import dev.zaqueu.moviefinder.data.remote.mappers.mapToShows
import dev.zaqueu.moviefinder.data.remote.services.TvMazeApi
import dev.zaqueu.moviefinder.domain.models.Show
import dev.zaqueu.moviefinder.domain.repositories.ShowsRepository

class ShowsRepositoryImpl(
    private val api: TvMazeApi
): ShowsRepository {
    override suspend fun getAllShows(page: Int): Result<List<Show>> {
        return try {
            val shows = api.getAllMovies(page).mapToShows()
            Result.success(shows)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun searchShow(name: String): Result<List<Show>> {
        return try {
            val shows = api.searchShow(name).mapToShows()
            Result.success(shows)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}
