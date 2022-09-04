package dev.zaqueu.moviefinder.domain.repositories

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.zaqueu.moviefinder.domain.models.Show
import kotlinx.coroutines.flow.Flow

interface ShowsRepository {
    fun getAllShows(
        pageConfig: PagingConfig = getDefaultPageConfig()
    ): Flow<PagingData<Show>>
    suspend fun searchShow(name: String): Result<List<Show>>

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
    }
}
