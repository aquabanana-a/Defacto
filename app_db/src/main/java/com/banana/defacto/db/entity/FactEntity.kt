package com.banana.defacto.db.entity

import androidx.room.Entity
import androidx.room.ColumnInfo
import com.banana.defacto.db.common.NumberType

@Entity(tableName = FactEntity.TABLE_NAME, primaryKeys = [FactEntity.COLUMN_NUMBER_NAME, FactEntity.COLUMN_NUMBER_TYPE_NAME])
data class FactEntity(
    @ColumnInfo(name = COLUMN_NUMBER_NAME) val number: Int,
    @ColumnInfo(name = COLUMN_NUMBER_TYPE_NAME) val type: NumberType,
    @ColumnInfo(name = COLUMN_NUMBER_DESCRIPTION_NAME) val description: String
) {
    companion object {
        const val TABLE_NAME = "fact"
        const val COLUMN_NUMBER_NAME = "num"
        const val COLUMN_NUMBER_TYPE_NAME = "num_type"
        const val COLUMN_NUMBER_DESCRIPTION_NAME = "num_text"
    }
}