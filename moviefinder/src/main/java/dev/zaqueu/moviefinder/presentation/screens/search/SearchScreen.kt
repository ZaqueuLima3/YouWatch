package dev.zaqueu.moviefinder.presentation.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import dev.zaqueu.moviefinder.R
import dev.zaqueu.moviefinder.presentation.components.DefaultTextField
import dev.zaqueu.moviefinder.presentation.components.MovieList
import dev.zaqueu.ui.theme.LocalSpacing
import kotlinx.coroutines.flow.first

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(spacing.spaceMedium)
                .fillMaxSize()
        ) {
            Text(
                text = "Find your movies",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )
            DefaultTextField(
                value = viewModel.searchQuery,
                onValueChange = viewModel::onSearchType
            )

            Spacer(modifier = Modifier.height(spacing.spaceLarge))

            if (viewModel.searchQuery.isBlank()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.searching),
                        contentDescription = null,
                        modifier = Modifier
                            .width(IntrinsicSize.Min),
                    )
                }
            } else {
                Box(
                    modifier= Modifier
                        .fillMaxSize()
                        .navigationBarsPadding(),
                    contentAlignment = Alignment.Center
                ) {
                    MovieList(
                        shows = viewModel.showsFlow,
                        onItemClick = { }
                    )
                }
            }
        }
    }
}
