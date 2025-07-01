package com.banana.defacto.model

import com.banana.defacto.data.Fact
import com.banana.defacto.data.NumberType
import io.reactivex.rxjava3.core.Single

interface FactsManager {
    fun getKnownFacts(): Single<List<Fact>>

    fun getFact(number: Int, type: NumberType): Single<Fact>

    fun getRandomFact(type: NumberType): Single<Fact>
}