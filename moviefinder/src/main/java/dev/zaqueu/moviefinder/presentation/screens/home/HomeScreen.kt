package dev.zaqueu.moviefinder.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.zaqueu.moviefinder.R
import dev.zaqueu.moviefinder.presentation.components.Header
import dev.zaqueu.moviefinder.presentation.components.MovieList
import dev.zaqueu.moviefinder.utils.extensions.capitalized
import dev.zaqueu.ui.theme.LocalSpacing
import dev.zaqueu.ui.utils.events.UiEvents

@Composable
fun HomeScreen(
    onNavigate: (UiEvents.Navigate) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    LaunchedEffect(key1 = true) {
        viewModel.fetchUserData()
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvents.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header(
            title = stringResource(id = R.string.hello, viewModel.username.capitalized()),
            subTitle = stringResource(id = R.string.find_your_next_movie)
        )

        Spacer(modifier = Modifier.height(spacing.spaceSmall))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceMedium),
            contentAlignment = Alignment.Center
        ) {
            MovieList(
                shows = viewModel.showsFlow,
                onItemClick = { show ->
                    viewModel.onItemClicked(show)
                }
            )
        }
    }
}
