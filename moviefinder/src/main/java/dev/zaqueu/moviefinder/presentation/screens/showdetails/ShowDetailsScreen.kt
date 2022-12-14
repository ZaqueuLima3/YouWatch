package dev.zaqueu.moviefinder.presentation.screens.showdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.zaqueu.moviefinder.R
import dev.zaqueu.moviefinder.presentation.components.Cover
import dev.zaqueu.moviefinder.presentation.components.Loading
import dev.zaqueu.moviefinder.presentation.components.Summary
import dev.zaqueu.moviefinder.presentation.components.TabBarHeader
import dev.zaqueu.moviefinder.utils.extensions.capitalized
import dev.zaqueu.moviefinder.utils.extensions.parseHtml
import dev.zaqueu.ui.theme.LocalSpacing
import dev.zaqueu.ui.theme.Shapes
import dev.zaqueu.ui.utils.events.UiEvents

@Composable
fun ShowDetailsScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (UiEvents) -> Unit,
    showId: String?,
    viewModel: ShowDetailsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = true) {
        if (showId != null) {
            viewModel.onEvent(
                ShowDetailsEvent.OnEnterScreen(showId)
            )
        }
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvents.Navigate -> onNavigate(event)
                is UiEvents.Pop -> onNavigate(event)
                is UiEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.toString(context)
                    )
                }
                else -> {}
            }
        }
    }

    if (viewModel.detailState.isLoading) {
        Loading(
            modifier = Modifier
                .fillMaxSize()
        )
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val show = viewModel.detailState.show

        if (show != null) {
            TabBarHeader(
                title = show.name,
                icon = if (show.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                onBackClick = { viewModel.onEvent(ShowDetailsEvent.OnBackClick) },
                onIconClick = { viewModel.onEvent(ShowDetailsEvent.OnFavoriteClick(show)) },
                iconTint = MaterialTheme.colors.primary
            )

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(spacing.spaceMedium)
            ) {
                Spacer(modifier = Modifier.height(spacing.spaceLarge))

                Cover(
                    image = show.cover,
                    modifier = Modifier
                        .height(300.dp)
                        .width(280.dp)
                        .clip(Shapes.large)
                        .align(Alignment.CenterHorizontally)
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing.spaceSmall),
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(show.genres) { item ->
                        Text(
                            text = item,
                            style = MaterialTheme.typography.body2,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    }
                }

                Spacer(modifier = Modifier.height(spacing.spaceSmall))

                if (show.premiered != null && show.ended != null) {
                    Text(
                        text = stringResource(
                            id = R.string.from,
                            show.premiered.month.name.capitalized(),
                            show.premiered.year
                        ),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = stringResource(
                            id = R.string.to,
                            show.ended.month.name.capitalized(),
                            show.ended.year
                        ),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Medium
                    )

                }

                Spacer(modifier = Modifier.height(spacing.spaceMedium))

                Summary(summary = show.summary.parseHtml())

                Spacer(modifier = Modifier.height(spacing.spaceMedium))

                Button(
                    onClick = {
                        viewModel.onEvent(ShowDetailsEvent.OnSeeEpisodesClick(show.id.toString()))
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = Shapes.large
                ) {
                    Text(
                        text = stringResource(id = R.string.see_all_episodes),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.padding(spacing.spaceSmall)
                    )
                }

            }
        }
    }
}
