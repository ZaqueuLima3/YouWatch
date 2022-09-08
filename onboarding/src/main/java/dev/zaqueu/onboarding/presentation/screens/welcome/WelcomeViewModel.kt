package dev.zaqueu.onboarding.presentation.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zaqueu.core.domain.preferences.Preferences
import dev.zaqueu.core.navigation.NavRoutes
import dev.zaqueu.ui.utils.events.UiEvents
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {
    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onGetStartedClick() {
        viewModelScope.launch {
            preferences.saveShouldShowWelcomeScreen(false)
            _uiEvent.send(
                UiEvents.Navigate(NavRoutes.PIN.route)
            )
        }
    }
}
