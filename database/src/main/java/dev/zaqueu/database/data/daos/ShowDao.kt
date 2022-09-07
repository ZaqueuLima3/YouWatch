package dev.zaqueu.database.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import dev.zaqueu.database.data.aggregate.ShowAggregate
import dev.zaqueu.database.data.entities.GenreEntity
import dev.zaqueu.database.data.entities.ShowEntity

@Dao
interface ShowDao {
    @Transaction
    @Query("SELECT * FROM ${ShowEntity.TABLE_NAME}")
    suspend fun getAllShows(): List<ShowAggregate>

    @Query("SELECT ${ShowEntity.COLUMN_ID} FROM ${ShowEntity.TABLE_NAME}")
    suspend fun getAllShowsIds(): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShow(
        show: ShowEntity,
        genres: List<GenreEntity>
    )

    suspend fun insertShowAggregate(showAggregate: ShowAggregate) {
        insertShow(
            show = showAggregate.show,
            genres = showAggregate.genres
        )
    }

    @Delete
    suspend fun deleteShow(show: ShowEntity)
}
