package dev.zaqueu.onboarding.presentation.screens.pinscreet

sealed class PinSecretEvent {
    object OnEnterScreen : PinSecretEvent()
    object OnSetUserData : PinSecretEvent()
    data class OnPinChange(val pin: String) : PinSecretEvent()
    data class OnUsernameChange(val username: String) : PinSecretEvent()
    object OnPinUnlockClick : PinSecretEvent()
    object OnBiometricUnlock : PinSecretEvent()
    object OnTogglePinVisibility : PinSecretEvent()
}
