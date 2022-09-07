package dev.zaqueu.moviefinder.presentation.screens.showdetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zaqueu.core.navigation.NavRoutes
import dev.zaqueu.moviefinder.R
import dev.zaqueu.moviefinder.domain.usecases.GetShow
import dev.zaqueu.ui.utils.events.UiEvents
import dev.zaqueu.ui.utils.text.TextResource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel @Inject constructor(
    private val getShow: GetShow
) : ViewModel() {
    var detailState by mutableStateOf(ShowDetailsState())
        private set

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ShowDetailsEvent) {
        when (event) {
            is ShowDetailsEvent.OnEnterScreen -> fetchDetails(event.showId)
            is ShowDetailsEvent.OnBackClick -> onBackClick()
            is ShowDetailsEvent.OnSeeEpisodesClick -> onSeeEpisodesClick(event.showId)
        }
    }

    private fun fetchDetails(showId: String) {
        viewModelScope.launch {
            getShow(showId)
                .onSuccess { show ->
                    detailState = detailState.copy(
                        show = show,
                        isLoading = false
                    )
                }
                .onFailure {
                    detailState = detailState.copy(isLoading = false)
                    _uiEvent.send(
                        UiEvents.ShowSnackBar(TextResource.StringResource(R.string.something_went_wrong))
                    )
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
            _uiEvent.send(UiEvents.Pop)
        }
    }
}
