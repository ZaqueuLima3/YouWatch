package dev.zaqueu.moviefinder.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.zaqueu.moviefinder.data.remote.dtos.PagingShowsDto
import dev.zaqueu.moviefinder.data.remote.mappers.mapToEpisode
import dev.zaqueu.moviefinder.data.remote.mappers.mapToModel
import dev.zaqueu.moviefinder.data.remote.mappers.mapToShows
import dev.zaqueu.moviefinder.data.remote.services.TvMazeApi
import dev.zaqueu.moviefinder.domain.models.Episode
import dev.zaqueu.moviefinder.domain.models.Show
import dev.zaqueu.moviefinder.domain.repositories.ShowsRepository
import kotlinx.coroutines.flow.Flow

class ShowsRepositoryImpl(
    private val api: TvMazeApi
) : ShowsRepository {
    override fun getAllShows(pageConfig: PagingConfig): Flow<PagingData<Show>> {
        return Pager(config = pageConfig) {
            ShowsPagingSource { page ->
                PagingShowsDto(
                    shows = api.getAllMovies(page).mapToShows(),
                    isPaginated = true
                )
            }
        }.flow
    }

    override fun searchShow(
        name: String,
        pageConfig: PagingConfig
    ): Flow<PagingData<Show>> {
        return Pager(config = pageConfig) {
            ShowsPagingSource {
                PagingShowsDto(
                    shows = api.searchShow(name).mapToShows(),
                    isPaginated = false
                )
            }
        }.flow
    }

    override suspend fun getShow(showId: String): Result<Show> {
        return try {
            val result = api.getShow(showId).mapToModel()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getShowEpisodes(showId: String): Result<Map<Int, List<Episode>>> {
        return try {
            val result = api
                .getShowEpisodes(showId)
                .mapToEpisode()
                .groupBy { it.season }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getEpisode(episodeId: String): Result<Episode> {
        return try {
            val episode = api.getEpisode(episodeId).mapToEpisode()
            Result.success(episode)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
