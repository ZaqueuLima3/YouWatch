package dev.zaqueu.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.zaqueu.database.data.daos.ShowDao
import dev.zaqueu.database.data.entities.GenreEntity
import dev.zaqueu.database.data.entities.ShowEntity

@Database(
    entities = [
        ShowEntity::class,
        GenreEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val showDao: ShowDao
}
