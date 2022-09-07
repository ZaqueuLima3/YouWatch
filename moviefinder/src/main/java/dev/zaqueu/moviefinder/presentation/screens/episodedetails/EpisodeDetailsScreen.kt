package dev.zaqueu.moviefinder.presentation.screens.episodedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.zaqueu.moviefinder.R
import dev.zaqueu.moviefinder.presentation.components.Cover
import dev.zaqueu.moviefinder.presentation.components.Summary
import dev.zaqueu.moviefinder.presentation.components.TabBarHeader
import dev.zaqueu.moviefinder.utils.extensions.parseHtml
import dev.zaqueu.ui.theme.LocalSpacing
import dev.zaqueu.ui.theme.Shapes
import dev.zaqueu.ui.utils.events.UiEvents

@Composable
fun EpisodeDetailsScreen(
    onNavigate: (UiEvents) -> Unit,
    episodeId: String?,
    viewModel: EpisodeDetailsViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val episode = viewModel.episodeDetailState.episode
    LaunchedEffect(key1 = true) {
        if (episodeId != null) {
            viewModel.onEvent(
                EpisodeDetailsEvent.OnEnterScreen(episodeId)
            )
        }
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvents.Pop -> onNavigate(event)
                else -> {}
            }
        }
    }

    if (episode != null) {
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(spacing.spaceMedium),
        ) {
            TabBarHeader(
                onBackClick = { },
                title = "${episode.number} - ${episode.name}"
            )

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(spacing.spaceMedium)
            ) {
                Cover(
                    image = episode.image,
                    modifier = Modifier
                        .height(300.dp)
                        .width(280.dp)
                        .clip(Shapes.large)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${stringResource(id = R.string.season)} - ${episode.season}",
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(spacing.spaceMedium))

                Summary(summary = episode.summary.parseHtml())
            }
        }
    }
}
