package com.banana.defacto.db.common

import androidx.room.TypeConverter

class NumberTypeConverter {
    @TypeConverter
    fun toNumberType(id: Int): NumberType = NumberType.fromId(id)

    @TypeConverter
    fun fromNumberType(type: NumberType): Int = type.id
}