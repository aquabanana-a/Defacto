package com.banana.defacto.db

import com.banana.defacto.db.common.NumberType
import com.banana.defacto.db.entity.FactEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

interface FactsDb {
    fun getFacts(): Maybe<List<FactEntity>>

    fun getFact(number: Int, type: NumberType): Maybe<FactEntity>

    fun insertOrUpdate(entities: List<FactEntity>): Completable

    fun insertOrUpdate(entity: FactEntity): Completable
}