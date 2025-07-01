package com.banana.defacto.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.banana.defacto.db.common.NumberType
import com.banana.defacto.db.entity.FactEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

@Dao
interface FactsDao {
    @Query("SELECT * FROM ${FactEntity.TABLE_NAME}")
    fun getFacts(): Maybe<List<FactEntity>>

    @Query("SELECT * FROM ${FactEntity.TABLE_NAME} WHERE ${FactEntity.COLUMN_NUMBER_NAME} = :number AND ${FactEntity.COLUMN_NUMBER_TYPE_NAME} = :type")
    fun getFact(number: Int, type: NumberType): Maybe<FactEntity>

    @Upsert
    fun insertOrUpdate(entities: List<FactEntity>): Completable

    @Upsert
    fun insertOrUpdate(entity: FactEntity): Completable
}