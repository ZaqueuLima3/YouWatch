package dev.zaqueu.moviefinder.data.repositories

import dev.zaqueu.moviefinder.data.remote.mappers.mapToShows
import dev.zaqueu.moviefinder.data.remote.services.TvMazeApi
import dev.zaqueu.moviefinder.domain.models.Show
import dev.zaqueu.moviefinder.domain.repositories.ShowsRepository
import dev.zaqueu.moviefinder.fixtures.createSearchShowsDto
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
//        val mockShows = listOf(
//            createShowDto(id = 1),
//            createShowDto(id = 2),
//        )
//
//        coEvery {
//            api.getAllMovies(any())
//        } returns mockShows
//
//        val expected = Page(
//            data = listOf(mockShows[0].mapToModel(), mockShows[1].mapToModel()),
//            prevKey = 2,
//            nextKey = null
//        )
//
//        val result = showsRepository.getAllShows().first()
//
//        Assert.assertEquals(result, expected)
//
//        coVerify {
//            api.getAllMovies(any())
//        }
//        confirmVerified(api)
    }

    @Test
    fun `should return Result of failure when call getAllMovies and it do not succeed`() =
        runBlocking {
//        val page = 1
//        val exception = Exception()
//        coEvery {
//            api.getAllMovies(any())
//        } throws(exception)
//
//        val result = showsRepository.getAllShows(page)
//
//        coVerify {
//            api.getAllMovies(page)
//        }
//        Assert.assertTrue(result.isFailure)
//        Assert.assertEquals(result.exceptionOrNull(), exception)
//        confirmVerified(api)
        }

    @Test
    fun `should return Result of all shows when call searchShow and it succeed`() = runBlocking {
        val name = "any-name"
        val showsDtos = listOf(
            createSearchShowsDto(createShowDto())
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
    fun `should return Result of failure when call searchShow and it do not succeed`() =
        runBlocking {
            val name = "any-name"
            val exception = Exception()
            coEvery {
                api.searchShow(any())
            } throws (exception)

            val result = showsRepository.searchShow(name)

            coVerify {
                api.searchShow(name)
            }
            Assert.assertTrue(result.isFailure)
            Assert.assertEquals(result.exceptionOrNull(), exception)
            confirmVerified(api)
        }
}
