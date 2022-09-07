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
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import dev.zaqueu.moviefinder.R
import dev.zaqueu.moviefinder.presentation.components.DetailsCover
import dev.zaqueu.moviefinder.presentation.components.Summary
import dev.zaqueu.moviefinder.presentation.components.TabBarHeader
import dev.zaqueu.moviefinder.utils.extensions.parseHtml
import dev.zaqueu.ui.theme.LocalSpacing
import dev.zaqueu.ui.theme.Shapes
import dev.zaqueu.ui.utils.events.UiEvents

@Composable
fun ShowDetailsScreen(
    onNavigate: (UiEvents.Navigate) -> Unit,
    showId: String?,
    viewModel: ShowDetailsViewModel = hiltViewModel()
) {
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
                else -> {}
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val show = viewModel.detailState.show
        TabBarHeader(
            title = show?.name.orEmpty(),
            icon = Icons.Filled.FavoriteBorder,
            onBackClick = { viewModel.onEvent(ShowDetailsEvent.OnBackClick) }
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(spacing.spaceMedium)
        ) {
            if (show != null) {
                Spacer(modifier = Modifier.height(spacing.spaceLarge))

                DetailsCover(image = show.cover)

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
                        text = """
                        ${stringResource(id = R.string.from)}: ${show.premiered.month.name.lowercase()} of ${show.premiered.year}, 
                        ${stringResource(id = R.string.to)}: ${show.ended.month.name.lowercase()} of ${show.ended.year}
                        """.trimIndent(),
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
                    )
                }
            }
        }
    }
}
