package dev.zaqueu.moviefinder.presentation.screens.episodedetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zaqueu.moviefinder.domain.usecases.GetEpisode
import dev.zaqueu.ui.utils.events.UiEvents
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailsViewModel @Inject constructor(
    private val getEpisode: GetEpisode
) : ViewModel() {
    var episodeDetailState by mutableStateOf(EpisodeDetailsState())
        private set

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: EpisodeDetailsEvent) {
        when (event) {
            is EpisodeDetailsEvent.OnEnterScreen -> fetchDetails(event.showId)
            is EpisodeDetailsEvent.OnBackClick -> onBackClick()
        }
    }

    private fun fetchDetails(episodeId: String) {
        viewModelScope.launch {
            getEpisode(episodeId)
                .onSuccess { episode ->
                    episodeDetailState = episodeDetailState.copy(
                        episode = episode,
                        isLoading = false
                    )
                }
                .onFailure {
                    episodeDetailState = episodeDetailState.copy(isLoading = false)
                }
        }
    }

    private fun onBackClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvents.Pop)
        }
    }
}
