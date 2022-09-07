package dev.zaqueu.database.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = GenreEntity.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = ShowEntity::class,
            parentColumns = [ShowEntity.COLUMN_ID],
            childColumns = [GenreEntity.SHOW_ID],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(ShowEntity.COLUMN_ID)]
)
data class GenreEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Int? = null,
    @ColumnInfo(name = SHOW_ID)
    val showId: Long,
    val name: String
) {
    companion object {
        const val TABLE_NAME = "genres"
        const val COLUMN_ID = "genreId"
        const val SHOW_ID = "showId"
    }
}
