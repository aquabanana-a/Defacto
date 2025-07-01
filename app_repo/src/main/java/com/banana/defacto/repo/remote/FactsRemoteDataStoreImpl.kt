package com.banana.defacto.repo.remote

import com.banana.defacto.api.NumbersApiService
import com.banana.defacto.data.Fact
import com.banana.defacto.data.NumberType
import com.banana.defacto.repo.mapper.FactDtoMapper
import com.banana.defacto.repo.mapper.NumberTypeMapper
import io.reactivex.rxjava3.core.Single

class FactsRemoteDataStoreImpl(
    private val service: NumbersApiService
): FactsRemoteDataStore {
    override fun getFact(number: Int, type: NumberType): Single<Fact> =
        service.getNumberFact(number, NumberTypeMapper.toDto(type))
            .map { FactDtoMapper.transformToModel(it) }

    override fun getRandomFact(type: NumberType): Single<Fact> =
        service.getRandomNumberFact(NumberTypeMapper.toDto(type))
            .map { FactDtoMapper.transformToModel(it) }
}