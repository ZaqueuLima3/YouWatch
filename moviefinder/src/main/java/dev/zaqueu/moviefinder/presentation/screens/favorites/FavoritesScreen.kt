package dev.zaqueu.moviefinder.presentation.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.zaqueu.moviefinder.R
import dev.zaqueu.moviefinder.presentation.components.Header
import dev.zaqueu.moviefinder.presentation.components.Loading
import dev.zaqueu.moviefinder.presentation.components.MovieCard
import dev.zaqueu.ui.theme.LocalSpacing
import dev.zaqueu.ui.utils.events.UiEvents

@Composable
fun FavoritesScreen(
    onNavigate: (UiEvents) -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    LaunchedEffect(key1 = true) {
        viewModel.onEvent(FavoritesEvent.OnEnterScreen)
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvents.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    if (viewModel.favoritesState.isLoading) {
        Loading(
            modifier = Modifier
                .fillMaxSize()
        )
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header(
            title = stringResource(id = R.string.favorite_show),
            subTitle = stringResource(id = R.string.all_your_favorite_movies)
        )

        Spacer(modifier = Modifier.height(spacing.spaceSmall))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceMedium)
                .navigationBarsPadding(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(viewModel.favoritesState.shows) { show ->
                    MovieCard(
                        show,
                        onItemClick = {
                            viewModel.onEvent(
                                FavoritesEvent.OnItemClick(show)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(spacing.spaceMedium))
                }
            }
        }
    }
}
