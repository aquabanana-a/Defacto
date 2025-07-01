package com.banana.defacto.view.factDetails

import com.banana.defacto.data.Fact
import com.banana.defacto.view.base.BasePresenterImpl

class FactDetailsPresenterImpl: BasePresenterImpl<FactDetailsContract.View, Any>(), FactDetailsContract.Presenter {

    private lateinit var fact: Fact

    override fun attachView(view: FactDetailsContract.View, fact: Fact) {
        super.attachView(view)
        this.fact = fact

        view?.showFact(this.fact)
    }
}