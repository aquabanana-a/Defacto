package com.banana.defacto.model

import com.banana.defacto.data.Fact
import com.banana.defacto.data.NumberType
import com.banana.defacto.repo.FactsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class FactsManagerImpl(
    private val repository: FactsRepository
) : FactsManager {

    data class FactWithOrder(
        val fact: Fact
    ) {
        var order: Int
        private set;

        companion object {
            private var index: Int = 0
        }

        init {
            order = index++
        }
    }

    private val knownFacts = hashMapOf<Int, HashMap<NumberType, FactWithOrder>>()

    private fun cacheFact(fact: Fact) = cacheFacts(arrayListOf(fact))

    private fun cacheFacts(facts: List<Fact>) {
        facts.forEach { fact ->
            if (!knownFacts.containsKey(fact.number))
                knownFacts[fact.number] = hashMapOf()
            if (!knownFacts[fact.number]!!.containsKey(fact.type))
                knownFacts[fact.number]!![fact.type] = FactWithOrder(fact)
        }
    }

    override fun getKnownFacts(): Single<List<Fact>> {
        if (knownFacts.isNotEmpty())
            return Single.just(knownFacts.values
                .flatMap { it.values }
                .sortedBy { it.order }
                .map { it.fact })

        return repository.getKnownFacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { cacheFacts(it) }
    }

    override fun getFact(number: Int, type: NumberType): Single<Fact> {
        return knownFacts.get(number)?.get(type)?.fact?.run { Single.just(this) }
            ?: repository.getFact(number, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess { cacheFact(it) }
    }

    override fun getRandomFact(type: NumberType): Single<Fact> {
        return repository.getRandomFact(type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { cacheFact(it) }
    }
}