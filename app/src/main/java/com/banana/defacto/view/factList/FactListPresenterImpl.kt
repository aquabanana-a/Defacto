package com.banana.defacto.view.factList

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.banana.defacto.data.Fact
import com.banana.defacto.data.NumberType
import com.banana.defacto.model.FactsManager
import com.banana.defacto.view.base.BasePresenterImpl
import io.reactivex.rxjava3.disposables.CompositeDisposable

class FactListPresenterImpl(
    private val factsManager: FactsManager
) : BasePresenterImpl<FactListContract.View, FactListContract.Router>(),
    FactListContract.Presenter, DefaultLifecycleObserver {

    private lateinit var cd: CompositeDisposable

    override fun handleGetFactClick(number: Int, type: NumberType) {
        cd.add(
            factsManager.getFact(number, type)
                .doOnSubscribe { view?.showLoading(true) }
                .doFinally { view?.showLoading(false) }
                .subscribe(
                    { fact ->
                        router?.openFactDetailsFragment(fact)
                    },
                    { _ ->
                        view?.showSomethingWentWrongAlert()
                    })
        )
    }

    override fun handleGetRandomFactClick(type: NumberType) {
        cd.add(
            factsManager.getRandomFact(type)
                .doOnSubscribe { view?.showLoading(true) }
                .doFinally { view?.showLoading(false) }
                .subscribe(
                    { fact ->
                        router?.openFactDetailsFragment(fact)
                    },
                    { _ ->
                        view?.showSomethingWentWrongAlert()
                    })
        )
    }

    override fun handleHistoryFactClick(fact: Fact) {
        handleGetFactClick(fact.number, fact.type)
    }

    override fun attachView(view: FactListContract.View) {
        super.attachView(view)
        cd = CompositeDisposable()
    }

    override fun detachView() {
        super.detachView()
        cd.dispose()
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)

        cd.add(
            factsManager.getKnownFacts()
                .subscribe(
                    { facts ->
                        view?.updateFactHistory(facts)
                    },
                    { _ ->
                        view?.showSomethingWentWrongAlert()
                    })
        )
    }
}