package com.banana.defacto.repo.local

import com.banana.defacto.data.Fact
import com.banana.defacto.data.NumberType
import com.banana.defacto.repo.mapper.FactEntityMapper
import com.banana.defacto.repo.mapper.NumberTypeMapper
import com.banana.defacto.db.FactsDb
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

class FactsLocalDataStoreImpl(
    private val db: FactsDb,
): FactsLocalDataStore {

    override fun getFacts(): Maybe<List<Fact>> = db.getFacts()
        .map { list -> list.map { FactEntityMapper.toModel(it) } }

    override fun getFact(number: Int, type: NumberType): Maybe<Fact> = db.getFact(number, NumberTypeMapper.toEntity(type))
        .map { FactEntityMapper.toModel(it) }

    override fun storeFact(fact: Fact): Completable = db.insertOrUpdate(FactEntityMapper.toEntity(fact))
}