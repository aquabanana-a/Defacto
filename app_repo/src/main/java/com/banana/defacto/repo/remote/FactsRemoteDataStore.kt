package com.banana.defacto.repo.remote

import com.banana.defacto.data.Fact
import com.banana.defacto.data.NumberType
import io.reactivex.rxjava3.core.Single

interface FactsRemoteDataStore {
    fun getFact(number: Int, type: NumberType): Single<Fact>

    fun getRandomFact(type: NumberType): Single<Fact>
}