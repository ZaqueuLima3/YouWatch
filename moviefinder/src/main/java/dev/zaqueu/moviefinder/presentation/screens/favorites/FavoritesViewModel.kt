package dev.zaqueu.moviefinder.presentation.screens.favorites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zaqueu.core.navigation.NavRoutes
import dev.zaqueu.moviefinder.domain.models.Show
import dev.zaqueu.moviefinder.domain.usecases.GetAllShowsFromFavorites
import dev.zaqueu.moviefinder.domain.usecases.SearchShows
import dev.zaqueu.moviefinder.presentation.screens.showdetails.ShowDetailsEvent
import dev.zaqueu.moviefinder.utils.helpers.debounce
import dev.zaqueu.ui.utils.events.UiEvents
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getAllShowsFromFavorites: GetAllShowsFromFavorites
) : ViewModel() {
    var favoritesState by mutableStateOf(FavoritesState())
        private set

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: FavoritesEvent) {
        when (event) {
            is FavoritesEvent.OnEnterScreen -> fetchFavorites()
            is FavoritesEvent.OnItemClick -> onItemClicked(event.show)
            else -> {}
        }
    }

    private fun fetchFavorites() {
        viewModelScope.launch {
            val shows = getAllShowsFromFavorites()
            favoritesState = favoritesState.copy(
                shows = shows,
                isLoading = false
            )
        }
    }

    private fun onItemClicked(show: Show) {
        viewModelScope.launch {
            _uiEvent.send(
                UiEvents.Navigate(
                    "${NavRoutes.DETAILS.route}/${show.id}"
                )
            )
        }
    }
}
