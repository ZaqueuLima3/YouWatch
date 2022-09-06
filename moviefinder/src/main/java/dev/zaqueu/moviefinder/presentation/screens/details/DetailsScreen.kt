package dev.zaqueu.moviefinder.presentation.screens.details

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import dev.zaqueu.moviefinder.R
import dev.zaqueu.moviefinder.utils.extensions.parseHtml
import dev.zaqueu.ui.theme.LocalSpacing
import dev.zaqueu.ui.theme.Shapes
import dev.zaqueu.ui.utils.events.UiEvents

@Composable
fun DetailsScreen(
    onNavigate: (UiEvents.Navigate) -> Unit,
    showId: String?,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = true) {
        if (showId != null) {
            viewModel.onEvent(
                DetailsEvent.OnEnterScreen(showId)
            )
        }
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvents.Navigate -> onNavigate(event)
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
        val show = viewModel.detailState.show
        val episodes = viewModel.detailState.episodes
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "back",
                modifier = Modifier
                    .clickable {
                        viewModel.onEvent(DetailsEvent.OnBackClick)
                    }
            )
            Text(
                text = show?.name.orEmpty(),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Icon(
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = "favorite"
            )
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(spacing.spaceMedium)
        ) {
            if (show != null) {
                Spacer(modifier = Modifier.height(spacing.spaceLarge))

                AsyncImage(
                    model = show.cover,
                    contentDescription = null,
                    modifier = Modifier
                        .height(300.dp)
                        .width(280.dp)
                        .clip(Shapes.large)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.placeholder),
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

                Spacer(modifier = Modifier.height(spacing.spaceMedium))

                Text(
                    text = stringResource(id = R.string.summary),
                    style = MaterialTheme.typography.h1,
                )

                if (show.premiered != null && show.ended != null) {
                    Text(
                        text = """
                        from: ${show.premiered.month.name.lowercase()} of ${show.premiered.year}, 
                        to: ${show.ended.month.name.lowercase()} of ${show.ended.year}
                        """.trimIndent(),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(spacing.spaceMedium))

                Text(
                    text = show.summary.parseHtml(),
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray,
                )

                Spacer(modifier = Modifier.height(spacing.spaceMedium))

                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = Shapes.large
                ) {
                    Text(
                        text = "See all episodes",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onPrimary,
                    )
                }
            }
        }
    }
}

// LazyColumn() {
//    items(episodes.keys.toList()) { season ->
//        Text(
//            text = season.toString(),
//            style = MaterialTheme.typography.body1,
//            color = Color.Gray,
//        )
//    }
//}
