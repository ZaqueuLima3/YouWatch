package dev.zaqueu.database.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = ShowEntity.TABLE_NAME
)
data class ShowEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: Long,
    val name: String,
    val cover: String?,
    val rating: Double?,
    val summary: String,
    val premiered: String?,
    val ended: String?
) {
    companion object {
        const val TABLE_NAME = "shows"
        const val COLUMN_ID = "showId"
    }
}
