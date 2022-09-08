package dev.zaqueu.onboarding.presentation.screens.pinscreet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import dev.zaqueu.onboarding.R
import dev.zaqueu.onboarding.presentation.components.DefaultButton
import dev.zaqueu.onboarding.presentation.components.PinTextField
import dev.zaqueu.ui.theme.LocalSpacing
import dev.zaqueu.ui.utils.events.UiEvents

@Composable
fun PinSecretScreen(
    onNavigate: (UiEvents) -> Unit,
    viewModel: PinSecretViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current as FragmentActivity

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(PinSecretEvent.OnEnterScreen)
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvents.NavigateAndClean -> onNavigate(event)
                is UiEvents.ShowBiometricPrompt -> {
                    event.biometric.show(context)
                }
                else -> {}
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val isUserDataSaved = viewModel.pinSecretState.isUserDataSaved
        Text(
            text = if (!isUserDataSaved) stringResource(id = R.string.create_an_account) else stringResource(
                id = R.string.enter_your_pin
            ),
            style = MaterialTheme.typography.h1
        )

        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        if (!isUserDataSaved) {
            OutlinedTextField(
                value = viewModel.pinSecretState.username,
                onValueChange = { value ->
                    viewModel.onEvent(
                        PinSecretEvent.OnUsernameChange(value)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text(text = stringResource(id = R.string.username)) },
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(spacing.spaceSmall))
        }

        PinTextField(
            value = viewModel.pinSecretState.pin,
            onValueChange = { value ->
                viewModel.onEvent(
                    PinSecretEvent.OnPinChange(value)
                )
            },
            label = { Text(text = stringResource(id = R.string.pin)) },
            isError = viewModel.pinSecretState.pinError,
            isPinVisible = viewModel.pinSecretState.pinTextInputIsVisible,
            onTogglePinVisibility = {
                viewModel.onEvent(
                    PinSecretEvent.OnTogglePinVisibility
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        DefaultButton(
            text = if (!isUserDataSaved) stringResource(id = R.string.save_account) else stringResource(
                id = R.string.enter_pin
            ),
            onClick = {
                if (!isUserDataSaved) {
                    viewModel.onEvent(
                        PinSecretEvent.OnSetUserData
                    )
                } else {
                    viewModel.onEvent(
                        PinSecretEvent.OnPinUnlockClick
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End),
            textStyle = MaterialTheme.typography.h3,
            isEnable = viewModel.pinSecretState.pinButtonEnabled
        )
    }
}
