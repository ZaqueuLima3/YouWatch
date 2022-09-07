package dev.zaqueu.moviefinder.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.zaqueu.moviefinder.domain.repositories.ShowsRepository
import dev.zaqueu.moviefinder.domain.usecases.GetAllShows
import dev.zaqueu.moviefinder.domain.usecases.GetEpisode
import dev.zaqueu.moviefinder.domain.usecases.GetEpisodes
import dev.zaqueu.moviefinder.domain.usecases.GetShow
import dev.zaqueu.moviefinder.domain.usecases.SearchShows

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

    @Provides
    @ViewModelScoped
    fun provideSearchShows(
        showsRepository: ShowsRepository
    ): SearchShows {
        return SearchShows(
            showsRepository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetShow(
        showsRepository: ShowsRepository
    ): GetShow {
        return GetShow(
            showsRepository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetEpisodes(
        showsRepository: ShowsRepository
    ): GetEpisodes {
        return GetEpisodes(
            showsRepository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetEpisode(
        showsRepository: ShowsRepository
    ): GetEpisode {
        return GetEpisode(
            showsRepository
        )
    }
}
