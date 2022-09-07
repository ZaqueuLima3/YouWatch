package dev.zaqueu.youwatch.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.zaqueu.core.data.preferences.DefaultPreferences
import dev.zaqueu.core.domain.preferences.Preferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun sharedPreferences(
        app: Application
    ): SharedPreferences {
        return app.getSharedPreferences("@you_watch", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun preferences(sharedPreferences: SharedPreferences): Preferences {
        return DefaultPreferences(sharedPreferences)
    }
}
