package com.banana.defacto.view.main

import com.banana.defacto.view.base.BasePresenter
import com.banana.defacto.view.base.BaseView

interface MainContract {

    interface View: BaseView {

    }

    interface Presenter: BasePresenter<View, Any> {

    }
}