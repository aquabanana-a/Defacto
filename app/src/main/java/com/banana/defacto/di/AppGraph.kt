package com.banana.defacto.di

import android.content.Context
import com.banana.defacto.model.di.ModelComponent
import com.banana.defacto.model.di.ModelGraph

object AppGraph {

    lateinit var modelComponent: ModelComponent
        private set

    lateinit var appComponent: AppComponent
        private set

    fun init(context: Context) {
        modelComponent = ModelGraph.create(context)

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(modelComponent))
            .build()
    }
}