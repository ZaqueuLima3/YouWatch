package dev.zaqueu.moviefinder.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zaqueu.core.navigation.NavRoutes
import dev.zaqueu.moviefinder.domain.models.Show
import dev.zaqueu.moviefinder.domain.usecases.GetAllShows
import dev.zaqueu.ui.utils.events.UiEvents
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getAllShows: GetAllShows
) : ViewModel() {
    val showsFlow = getAllShows()
        .cachedIn(viewModelScope)

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onItemClicked(show: Show) {
        viewModelScope.launch {
            _uiEvent.send(
                UiEvents.Navigate(
                    "${NavRoutes.DETAILS.route}/${show.id}"
                )
            )
        }
    }
}
