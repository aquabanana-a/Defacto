package com.banana.defacto.view.base

abstract class BasePresenterImpl<V, R> : BasePresenter<V, R> {
    protected var view: V? = null
    protected var router: R? = null

    val isViewAttached: Boolean
        get() = view != null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun attachRouter(router: R) {
        this.router = router
    }

    override fun detachRouter() {
        this.router = null
    }
}