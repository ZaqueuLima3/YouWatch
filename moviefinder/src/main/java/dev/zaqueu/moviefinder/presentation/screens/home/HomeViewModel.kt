package dev.zaqueu.moviefinder.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zaqueu.core.domain.preferences.Preferences
import dev.zaqueu.core.navigation.NavRoutes
import dev.zaqueu.moviefinder.domain.models.Show
import dev.zaqueu.moviefinder.domain.usecases.GetAllShows
import dev.zaqueu.moviefinder.presentation.screens.showdetails.ShowDetailsState
import dev.zaqueu.ui.utils.events.UiEvents
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getAllShows: GetAllShows,
    private val preferences: Preferences
) : ViewModel() {
    val showsFlow = getAllShows()
        .cachedIn(viewModelScope)

    var username by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun fetchUserData() {
        val userdata = preferences.loadUserData()
        username = userdata.username
    }

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
