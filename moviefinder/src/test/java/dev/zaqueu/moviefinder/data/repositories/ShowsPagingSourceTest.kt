package dev.zaqueu.moviefinder.data.repositories

import androidx.paging.PagingSource.LoadParams.Refresh
import androidx.paging.PagingSource.LoadResult
import dev.zaqueu.moviefinder.data.remote.dtos.PagingShowsDto
import dev.zaqueu.moviefinder.data.remote.mappers.mapToModel
import dev.zaqueu.moviefinder.data.remote.mappers.mapToShows
import dev.zaqueu.moviefinder.data.remote.services.TvMazeApi
import dev.zaqueu.moviefinder.domain.models.Show
import dev.zaqueu.moviefinder.fixtures.createShowDto
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ShowsPagingSourceTest {
    private val mockShows = listOf(
        createShowDto(id = 1),
        createShowDto(id = 2),
    )

    @MockK
    internal lateinit var api: TvMazeApi

    private lateinit var showsPagingSource: ShowsPagingSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `load returns page when on successful`() = runBlocking {
        coEvery {
            api.getAllMovies(any())
        } returns mockShows

        showsPagingSource = ShowsPagingSource { page ->
            val shows = api.getAllMovies(page).mapToShows()
            PagingShowsDto(
                shows = shows,
                isPaginated = true
            )
        }

        Assert.assertEquals(
            LoadResult.Page(
                data = listOf(mockShows[0].mapToModel(), mockShows[1].mapToModel()),
                prevKey = null,
                nextKey = 2
            ),
            showsPagingSource.load(
                Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            ),
        )

        coVerify {
            api.getAllMovies(any())
        }
        confirmVerified(api)
    }

    @Test
    fun `load returns failure when on fails`() = runBlocking {
        val exception =  Exception()
        coEvery {
            api.getAllMovies(any())
        } throws exception

        showsPagingSource = ShowsPagingSource { page ->
            val shows = api.getAllMovies(page).mapToShows()
            PagingShowsDto(
                shows = shows,
                isPaginated = true
            )
        }

        Assert.assertEquals(
            LoadResult.Error<Int, Show>(exception),
            showsPagingSource.load(
                Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            ),
        )

        coVerify {
            api.getAllMovies(any())
        }
        confirmVerified(api)
    }
}
