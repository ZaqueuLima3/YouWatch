package dev.zaqueu.onboarding.presentation.screens.pinscreet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zaqueu.core.domain.modules.User
import dev.zaqueu.core.domain.preferences.Preferences
import dev.zaqueu.core.navigation.NavRoutes
import dev.zaqueu.onboarding.domain.usecases.FilterDigits
import dev.zaqueu.ui.utils.biometric.BiometricEvent
import dev.zaqueu.ui.utils.events.UiEvents
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinSecretViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterDigits: FilterDigits
) : ViewModel(), DefaultLifecycleObserver {
    var pinSecretState by mutableStateOf(PinSecretState())
        private set

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var unlockPIN: String? = null

    fun onEvent(event: PinSecretEvent) {
        when (event) {
            is PinSecretEvent.OnEnterScreen -> fetchUserData()
            is PinSecretEvent.OnSetUserData -> onSetUserData()
            is PinSecretEvent.OnPinChange -> onPinChange(event.pin)
            is PinSecretEvent.OnUsernameChange -> onUsernameChange(event.username)
            is PinSecretEvent.OnTogglePinVisibility -> togglePinVisibility()
            is PinSecretEvent.OnPinUnlockClick -> onPinUnlock()
            is PinSecretEvent.OnBiometricUnlock -> navigateToHome()
        }
    }

    private fun fetchUserData() {
        viewModelScope.launch {
            val user = preferences.loadUserData()
            val isUserDataSaved = user.username.isNotBlank()
            unlockPIN = user.pin
            pinSecretState = pinSecretState.copy(
                username = user.username,
                isUserDataSaved = isUserDataSaved
            )
            if (isUserDataSaved) {
                _uiEvent.send(
                    UiEvents.ShowBiometricPrompt(
                        BiometricEvent.OnShowBiometric(
                            onSuccess = { navigateToHome() },
                            onError = {
                                pinSecretState = pinSecretState.copy(
                                    pinError = true
                                )
                            }
                        )
                    )
                )
            }
        }
    }

    private fun onSetUserData() {
        preferences.saveUserData(
            User(
                username = pinSecretState.username,
                pin = pinSecretState.pin
            )
        )
        navigateToHome()
    }

    private fun onPinUnlock() {
        if (pinSecretState.pin == unlockPIN) {
            navigateToHome()
            return
        }
        pinSecretState = pinSecretState.copy(
            pinError = pinSecretState.pin != unlockPIN
        )
    }

    private fun onUsernameChange(username: String) {
        pinSecretState = pinSecretState.copy(
            username = username
        )
    }

    private fun onPinChange(pin: String) {
        if (pin.length <= PIN_SIZE) {
            pinSecretState = pinSecretState.copy(
                pin = filterDigits(pin),
                pinButtonEnabled = pin.length == PIN_SIZE
            )
        }
    }

    private fun togglePinVisibility() {
        val updatedPinVisibility = !pinSecretState.pinTextInputIsVisible
        pinSecretState = pinSecretState.copy(
            pinTextInputIsVisible = updatedPinVisibility
        )
    }

    private fun navigateToHome() {
        viewModelScope.launch {
            preferences.saveShouldShowWelcomeScreen(false)
            _uiEvent.send(
                UiEvents.NavigateAndClean(
                    toRoute = NavRoutes.HOME.route,
                    fromRoute = NavRoutes.PIN.route
                )
            )
        }
    }

    companion object {
        private const val PIN_SIZE = 4
    }
}
