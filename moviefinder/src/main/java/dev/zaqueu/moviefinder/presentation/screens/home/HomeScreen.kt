package dev.zaqueu.moviefinder.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
    ) {
        Column(
        ) {
            Column(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primary),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${stringResource(id = R.string.hello)} Username,",
                    style = MaterialTheme.typography.h1,
                    color = Color.White,
                )
                Text(
                    text = stringResource(id = R.string.find_your_next_movie),
                    color = Color.LightGray,
                )
            }


            Spacer(modifier = Modifier.height(spacing.spaceSmall))

            Box(
                modifier= Modifier
                    .fillMaxSize()
                    .padding(spacing.spaceMedium),
                contentAlignment = Alignment.Center
            ) {
                MovieList(shows = viewModel.showsFlow)
            }
        }
    }
}
