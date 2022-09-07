package dev.zaqueu.moviefinder.domain.repositories

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.zaqueu.moviefinder.domain.models.Episode
import dev.zaqueu.moviefinder.domain.models.Show
import kotlinx.coroutines.flow.Flow

interface ShowsRepository {
    fun getAllShows(
        pageConfig: PagingConfig = getDefaultPageConfig()
    ): Flow<PagingData<Show>>

    fun searchShow(
        name: String,
        pageConfig: PagingConfig = getDefaultPageConfig()
    ): Flow<PagingData<Show>>

    suspend fun getShow(showId: String): Result<Show>

    suspend fun getShowEpisodes(showId: String): Result<Map<Int, List<Episode>>>

    suspend fun getEpisode(episodeId: String): Result<Episode>

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
    }
}
