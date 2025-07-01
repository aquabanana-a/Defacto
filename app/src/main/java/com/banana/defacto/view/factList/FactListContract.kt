package com.banana.defacto.view.factList

import com.banana.defacto.data.Fact
import com.banana.defacto.data.NumberType
import com.banana.defacto.view.base.BasePresenter
import com.banana.defacto.view.base.BaseRouter
import com.banana.defacto.view.base.BaseView

interface FactListContract {

    interface View: BaseView {
        fun updateFactHistory(facts: List<Fact>)
        fun showLoading(visible: Boolean)
        fun showSomethingWentWrongAlert()
    }

    interface Router: BaseRouter {
        fun openFactDetailsFragment(fact: Fact)
    }

    interface Presenter: BasePresenter<View, Router> {
        fun handleGetFactClick(number:Int, type:NumberType)
        fun handleGetRandomFactClick(type: NumberType)
        fun handleHistoryFactClick(fact: Fact)
    }
}