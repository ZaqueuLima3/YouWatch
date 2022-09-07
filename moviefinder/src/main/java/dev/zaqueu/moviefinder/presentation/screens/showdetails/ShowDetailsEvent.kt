package dev.zaqueu.moviefinder.presentation.screens.showdetails

import dev.zaqueu.moviefinder.domain.models.Show

sealed class ShowDetailsEvent {
    data class OnEnterScreen(val showId: String) : ShowDetailsEvent()
    data class OnSeeEpisodesClick(val showId: String) : ShowDetailsEvent()
    object OnBackClick : ShowDetailsEvent()
    data class OnFavoriteClick(val show: Show) : ShowDetailsEvent()
}
