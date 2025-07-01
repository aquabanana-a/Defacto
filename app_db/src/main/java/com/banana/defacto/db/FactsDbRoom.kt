package com.banana.defacto.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.banana.defacto.db.common.NumberTypeConverter
import com.banana.defacto.db.dao.FactsDao
import com.banana.defacto.db.entity.FactEntity

@Database(entities = [FactEntity::class], version = 1)
@TypeConverters(NumberTypeConverter::class)
abstract class FactsDbRoom : RoomDatabase() {
    abstract fun factsDao(): FactsDao
}