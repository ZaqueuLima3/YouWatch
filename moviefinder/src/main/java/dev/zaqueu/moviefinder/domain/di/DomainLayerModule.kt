package dev.zaqueu.moviefinder.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.zaqueu.moviefinder.domain.repositories.ShowsRepository
import dev.zaqueu.moviefinder.domain.usecases.GetAllShows

@Module
@InstallIn(ViewModelComponent::class)
object DomainLayerModule {
    @Provides
    @ViewModelScoped
    fun provideGetAllShows(
        showsRepository: ShowsRepository
    ): GetAllShows {
        return GetAllShows(
            showsRepository
        )
    }
}
