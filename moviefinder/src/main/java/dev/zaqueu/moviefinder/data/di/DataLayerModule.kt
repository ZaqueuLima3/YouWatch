package dev.zaqueu.moviefinder.data.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.zaqueu.database.data.daos.ShowDao
import dev.zaqueu.moviefinder.data.remote.services.TvMazeApi
import dev.zaqueu.moviefinder.data.repositories.FavoritesRepositoryImpl
import dev.zaqueu.moviefinder.data.repositories.ShowsRepositoryImpl
import dev.zaqueu.moviefinder.domain.repositories.FavoritesRepository
import dev.zaqueu.moviefinder.domain.repositories.ShowsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataLayerModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideTvMazeApi(
        client: OkHttpClient,
        moshi: Moshi
    ): TvMazeApi {
        return Retrofit.Builder()
            .baseUrl(TvMazeApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideShowsRepository(
        api: TvMazeApi,
        showDao: ShowDao
    ): ShowsRepository {
        return ShowsRepositoryImpl(
            api = api,
            showDao = showDao
        )
    }

    @Provides
    @Singleton
    fun provideFavoritesRepository(
        showDao: ShowDao
    ): FavoritesRepository {
        return FavoritesRepositoryImpl(
            showDao = showDao
        )
    }
}
