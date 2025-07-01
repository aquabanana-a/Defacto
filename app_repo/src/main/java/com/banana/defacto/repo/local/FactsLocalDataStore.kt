package com.banana.defacto.repo.local

import com.banana.defacto.data.Fact
import com.banana.defacto.data.NumberType
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

interface FactsLocalDataStore {
    fun getFacts(): Maybe<List<Fact>>

    fun getFact(number: Int, type: NumberType): Maybe<Fact>

    fun storeFact(fact: Fact): Completable
}