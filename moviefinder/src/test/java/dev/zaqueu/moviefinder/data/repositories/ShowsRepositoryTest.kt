package dev.zaqueu.moviefinder.data.repositories

import dev.zaqueu.moviefinder.data.remote.mappers.mapToShows
import dev.zaqueu.moviefinder.data.remote.services.TvMazeApi
import dev.zaqueu.moviefinder.domain.models.Show
import dev.zaqueu.moviefinder.domain.repositories.ShowsRepository
import dev.zaqueu.moviefinder.fixtures.createShowDto
import dev.zaqueu.moviefinder.fixtures.createShowsDto
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ShowsRepositoryTest {
    @MockK
    internal lateinit var api: TvMazeApi

    private lateinit var showsRepository: ShowsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        showsRepository = ShowsRepositoryImpl(api)
    }

    @Test
    fun `should return Result of all shows when call getAllMovies and it succeed`() = runBlocking {
        val page = 1
        val showsDtos = createShowsDto(
            listOf(createShowDto())
        )
        val expected: List<Show> = showsDtos.mapToShows()
        coEvery {
            api.getAllMovies(any())
        } returns showsDtos

        val result = showsRepository.getAllShows(page)

        coVerify {
            api.getAllMovies(page)
        }
        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals(result.getOrNull(), expected)
        confirmVerified(api)
    }

    @Test
    fun `should return Result of failure when call getAllMovies and it do not succeed`() = runBlocking {
        val page = 1
        val exception = Exception()
        coEvery {
            api.getAllMovies(any())
        } throws(exception)

        val result = showsRepository.getAllShows(page)

        coVerify {
            api.getAllMovies(page)
        }
        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(result.exceptionOrNull(), exception)
        confirmVerified(api)
    }

    @Test
    fun `should return Result of all shows when call searchShow and it succeed`() = runBlocking {
        val name = "any-name"
        val showsDtos = createShowsDto(
            listOf(createShowDto())
        )
        val expected: List<Show> = showsDtos.mapToShows()
        coEvery {
            api.searchShow(any())
        } returns showsDtos

        val result = showsRepository.searchShow(name)

        coVerify {
            api.searchShow(name)
        }
        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals(result.getOrNull(), expected)
        confirmVerified(api)
    }

    @Test
    fun `should return Result of failure when call searchShow and it do not succeed`() = runBlocking {
        val name = "any-name"
        val exception = Exception()
        coEvery {
            api.searchShow(any())
        } throws(exception)

        val result = showsRepository.searchShow(name)

        coVerify {
            api.searchShow(name)
        }
        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(result.exceptionOrNull(), exception)
        confirmVerified(api)
    }
}
