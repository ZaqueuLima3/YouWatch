package dev.zaqueu.moviefinder.presentation.screens.episodes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zaqueu.core.navigation.NavRoutes
import dev.zaqueu.moviefinder.domain.usecases.GetEpisodes
import dev.zaqueu.ui.utils.events.UiEvents
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val getEpisodes: GetEpisodes
) : ViewModel() {
    var episodesState by mutableStateOf(EpisodesState())
        private set

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: EpisodesEvent) {
        when (event) {
            is EpisodesEvent.OnEnterScreen -> fetchEpisodes(event.showId)
            is EpisodesEvent.OnBackClick -> onBackClick()
            is EpisodesEvent.OnEpisodesClick -> onEpisodesClick(event.episodeId)
        }
    }

    private fun fetchEpisodes(showId: String) {
        viewModelScope.launch {
            getEpisodes(showId)
                .onSuccess { episodes ->
                    episodesState = episodesState.copy(episodes = episodes)
                }
        }
    }

    private fun onEpisodesClick(episodeId: Int) {
        viewModelScope.launch {
            _uiEvent.send(UiEvents.Navigate("${NavRoutes.EPISODEDETAILS.route}/${episodeId}"))
        }
    }

    private fun onBackClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvents.Pop)
        }
    }
}
