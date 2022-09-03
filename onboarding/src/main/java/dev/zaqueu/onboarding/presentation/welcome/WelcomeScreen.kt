package dev.zaqueu.onboarding.presentation.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.zaqueu.core.navigation.Route
import dev.zaqueu.onboarding.R
import dev.zaqueu.onboarding.presentation.components.DefaultButton
import dev.zaqueu.ui.theme.LocalSpacing
import dev.zaqueu.ui.utils.events.UiEvents

@Composable
fun WelcomeScreen(
    onNavigate: (UiEvents.Navigate) -> Unit,
) {
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceMedium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.welcome_to),
                style = MaterialTheme.typography.h2
            )
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Text(
                text = stringResource(R.string.you_watch),
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Text(
                text = stringResource(R.string.discover_and_share_movies),
                style = MaterialTheme.typography.body1,
            )
        }

        DefaultButton(
            text = stringResource(R.string.get_stared),
            onClick = {
                onNavigate(UiEvents.Navigate(Route.WELCOME))
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.BottomEnd),
        )
    }
}
