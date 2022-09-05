package dev.zaqueu.moviefinder.domain.usecases

import androidx.paging.PagingData
import dev.zaqueu.moviefinder.domain.models.Show
import dev.zaqueu.moviefinder.domain.repositories.ShowsRepository
import kotlinx.coroutines.flow.Flow

class SearchShows(
    private val showsRepository: ShowsRepository
) {
    operator fun invoke(name: String): Flow<PagingData<Show>> {
        return showsRepository.searchShow(name)
    }
}
