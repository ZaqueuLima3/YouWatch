package dev.zaqueu.moviefinder.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zaqueu.moviefinder.domain.usecases.GetAllShows
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getAllShows: GetAllShows
) : ViewModel() {
    val showsFlow = getAllShows()
        .cachedIn(viewModelScope)
}
