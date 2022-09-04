package dev.zaqueu.moviefinder.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.zaqueu.moviefinder.R
import dev.zaqueu.moviefinder.presentation.components.MovieList
import dev.zaqueu.ui.theme.LocalSpacing
import dev.zaqueu.ui.utils.events.UiEvents

@Composable
fun HomeScreen(
    onNavigate: (UiEvents.Navigate) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium)
    ) {
        Column(
        ) {
            Text(
                text = "${stringResource(id = R.string.hello)} Username,",
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onBackground
            )
            Text(
                text = stringResource(id = R.string.find_your_next_movie),
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(spacing.spaceSmall))

            Box(
                modifier= Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                MovieList(shows = viewModel.showsFlow)
            }
        }
    }
}
