package dev.zaqueu.database.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.zaqueu.database.AppDatabase
import dev.zaqueu.database.data.daos.ShowDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "app_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShowDao(
        db: AppDatabase
    ): ShowDao {
        return db.showDao
    }
}
