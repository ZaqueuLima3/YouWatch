package dev.zaqueu.moviefinder.data.repositories

import dev.zaqueu.database.data.daos.ShowDao
import dev.zaqueu.moviefinder.data.local.mappers.mapToEntity
import dev.zaqueu.moviefinder.data.local.mappers.mapToModel
import dev.zaqueu.moviefinder.domain.models.Show
import dev.zaqueu.moviefinder.domain.repositories.FavoritesRepository

class FavoritesRepositoryImpl(
    private val showDao: ShowDao
) : FavoritesRepository {
    override suspend fun saveShowAsFavorite(show: Show) {
        showDao.insertShowAggregate(show.mapToEntity())
    }

    override suspend fun getAllShowsFromFavorites(): List<Show> {
        return showDao.getAllShows().mapToModel()
    }
}
