package com.banana.defacto.repo

import android.util.Log
import com.banana.defacto.data.Fact
import com.banana.defacto.data.NumberType
import com.banana.defacto.repo.local.FactsLocalDataStore
import com.banana.defacto.repo.remote.FactsRemoteDataStore
import io.reactivex.rxjava3.core.Single

class FactsRepositoryImpl(
    private val localDataStore: FactsLocalDataStore,
    private val remoteDataStore: FactsRemoteDataStore
) : FactsRepository {

    override fun getKnownFacts(): Single<List<Fact>> = localDataStore.getFacts()
        .defaultIfEmpty(arrayListOf())

    override fun getFact(number: Int, type: NumberType): Single<Fact> {
        return localDataStore.getFact(number, type)
            .switchIfEmpty(remoteDataStore.getFact(number, type).flatMap { fact ->
                localDataStore.storeFact(fact)
                    .doOnError { e -> Log.e("FactsRepository", "Failed to store fact", e) }
                    .onErrorComplete()
                    .andThen(Single.just(fact))
            })
    }

    override fun getRandomFact(type: NumberType): Single<Fact> {
        return remoteDataStore.getRandomFact(type).flatMap { fact ->
            localDataStore.storeFact(fact)
                .doOnError { e -> Log.e("FactsRepository", "Failed to store random fact", e) }
                .onErrorComplete()
                .andThen(Single.just(fact))
        }
    }
}
