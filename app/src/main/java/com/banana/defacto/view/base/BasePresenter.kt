package com.banana.defacto.view.base

interface BasePresenter<V, R> {

    fun attachView(view: V)

    fun detachView()

    fun attachRouter(router: R)

    fun detachRouter()
}