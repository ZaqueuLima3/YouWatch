package dev.zaqueu.moviefinder.presentation.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zaqueu.core.navigation.NavRoutes
import dev.zaqueu.moviefinder.domain.usecases.GetEpisodes
import dev.zaqueu.moviefinder.domain.usecases.GetShow
import dev.zaqueu.ui.utils.events.UiEvents
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getShow: GetShow
) : ViewModel() {
    var detailState by mutableStateOf(DetailsState())
        private set

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.OnEnterScreen -> fetchDetails(event.showId)
            is DetailsEvent.OnBackClick -> onBackClick()
            is DetailsEvent.OnSeeEpisodesClick -> onSeeEpisodesClick(event.showId)
        }
    }

    private fun fetchDetails(showId: String) {
        viewModelScope.launch {
            getShow(showId)
                .onSuccess { show ->
                    detailState = detailState.copy(show = show)
                }
        }
    }

    private fun onSeeEpisodesClick(showId: String) {
        viewModelScope.launch {
            _uiEvent.send(
                UiEvents.Navigate("${NavRoutes.EPISODES.route}/${showId}")
            )
        }
    }

    private fun onBackClick() {
        viewModelScope.launch {
            _uiEvent.send(
                UiEvents.Navigate(NavRoutes.HOME.route)
            )
        }
    }
}
