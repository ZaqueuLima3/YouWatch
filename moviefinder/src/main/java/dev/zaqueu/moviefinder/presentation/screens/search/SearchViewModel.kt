package dev.zaqueu.moviefinder.presentation.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zaqueu.moviefinder.domain.models.Show
import dev.zaqueu.moviefinder.domain.usecases.SearchShows
import dev.zaqueu.moviefinder.utils.helpers.debounce
import dev.zaqueu.ui.utils.events.UiEvents
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchShows: SearchShows
) : ViewModel() {
    var showsFlow: Flow<PagingData<Show>> = emptyFlow()

    var searchQuery by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val searchMovies: (String) -> Unit = debounce(
        300L,
        viewModelScope
    ) { query ->
        showsFlow = searchShows(query)
            .cachedIn(viewModelScope)
    }

    fun onSearchType(query: String) {
        searchQuery = query
        searchMovies(query)
    }
}
