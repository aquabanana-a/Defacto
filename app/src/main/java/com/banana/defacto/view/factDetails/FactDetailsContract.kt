package com.banana.defacto.view.factDetails

import com.banana.defacto.data.Fact
import com.banana.defacto.view.base.BasePresenter
import com.banana.defacto.view.base.BaseView

interface FactDetailsContract {

    interface View: BaseView {
        fun showFact(fact: Fact)
    }

    interface Presenter: BasePresenter<View, Any> {
        fun attachView(view: View, fact: Fact)
    }
}