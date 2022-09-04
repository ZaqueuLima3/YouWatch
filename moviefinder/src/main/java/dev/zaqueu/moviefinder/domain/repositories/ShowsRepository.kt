package dev.zaqueu.moviefinder.domain.repositories

import dev.zaqueu.moviefinder.domain.models.Show

interface ShowsRepository {
    suspend fun getAllShows(page: Int): Result<List<Show>>
    suspend fun searchShow(name: String): Result<List<Show>>
}
