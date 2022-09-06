package dev.zaqueu.moviefinder.presentation.screens.episodes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.zaqueu.moviefinder.R
import dev.zaqueu.moviefinder.presentation.components.EpisodeCard
import dev.zaqueu.ui.theme.LocalSpacing
import dev.zaqueu.ui.utils.events.UiEvents

@Composable
fun EpisodesScreen(
    onNavigate: (UiEvents) -> Unit,
    showId: String?,
    viewModel: EpisodesViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = true) {
        if (showId != null) {
            viewModel.onEvent(
                EpisodesEvent.OnEnterScreen(showId)
            )
        }
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvents.Pop -> onNavigate(event)
                else -> {}
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                PaddingValues(spacing.spaceMedium)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    PaddingValues(
                        vertical = spacing.spaceMedium
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
                modifier = Modifier
                    .clickable {
                        viewModel.onEvent(EpisodesEvent.OnBackClick)
                    }
            )
        }
        val episodes = viewModel.episodesState.episodes
        LazyColumn() {
            items(episodes.keys.toList()) { season ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "${stringResource(id = R.string.season)} $season",
                        style = MaterialTheme.typography.h1
                    )
                }

                val seasonEps = episodes[season].orEmpty()
                if (seasonEps.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(spacing.spaceMedium))
                    LazyRow() {
                        items(seasonEps) { ep ->
                            EpisodeCard(
                                name = "${ep.number} - ${ep.name}",
                                image = ep.image,
                                summary = ep.summary,
                                onItemClick = { }
                            )
                            Spacer(modifier = Modifier.width(spacing.spaceMedium))
                        }
                    }
                    Spacer(modifier = Modifier.height(spacing.spaceMedium))
                }

            }
        }
    }
}
