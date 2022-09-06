package dev.zaqueu.moviefinder.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dev.zaqueu.moviefinder.domain.models.Show
import dev.zaqueu.ui.theme.LocalSpacing
import kotlinx.coroutines.flow.Flow

@Composable
fun MovieList(
    shows: Flow<PagingData<Show>>,
    onItemClick: (item: Show) -> Unit
) {
    val lazyShowsItems = shows.collectAsLazyPagingItems()
    val spacing = LocalSpacing.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(lazyShowsItems) { show ->
            if (show != null) {
                MovieCard(
                    show,
                    onItemClick = onItemClick
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
            }
        }

        lazyShowsItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { Loading(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { }
                }
                loadState.refresh is LoadState.Error -> {
                    (loadState.refresh as LoadState.Error).error.printStackTrace()
                    item { }
                }
                loadState.append is LoadState.Error -> {
                    item { }
                }
            }
        }
    }
}
