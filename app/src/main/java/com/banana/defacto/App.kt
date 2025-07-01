package com.banana.defacto

import android.app.Application
import com.banana.defacto.di.AppGraph

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        AppGraph.init(applicationContext)
    }
}