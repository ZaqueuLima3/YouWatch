package dev.zaqueu.database.data.aggregate

import androidx.room.Embedded
import androidx.room.Relation
import dev.zaqueu.database.data.entities.GenreEntity
import dev.zaqueu.database.data.entities.ShowEntity

data class ShowAggregate(
    @Embedded
    val show: ShowEntity,
    @Relation(
        parentColumn = ShowEntity.COLUMN_ID,
        entityColumn = GenreEntity.SHOW_ID
    )
    val genres: List<GenreEntity>
)
