package dev.zaqueu.moviefinder.data.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.zaqueu.moviefinder.data.remote.dtos.PagingShowsDto
import dev.zaqueu.moviefinder.domain.models.Show

class ShowsPagingSource(
    private val callBack: suspend (page: Int) -> PagingShowsDto
) : PagingSource<Int, Show>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Show> {
        return try {
            val nextPage = params.key ?: 1
            val result = callBack(nextPage)

            val prevKey = if (nextPage == 1) null else nextPage - 1
            val nextKey =
                if (result.shows.isNotEmpty() && result.isPaginated) nextPage.plus(1) else null

            LoadResult.Page(
                data = result.shows,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Show>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}
