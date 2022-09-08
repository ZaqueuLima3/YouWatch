package dev.zaqueu.onboarding.presentation.screens.pinscreet

data class PinSecretState(
    val username: String = "",
    val pin: String = "",
    val isUserDataSaved: Boolean = false,
    val pinButtonEnabled: Boolean = false,
    val pinTextInputIsVisible: Boolean = false,
    val pinError: Boolean = false
)
