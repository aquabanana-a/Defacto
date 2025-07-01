package com.banana.defacto.di

import com.banana.defacto.model.FactsManager
import com.banana.defacto.model.di.ModelComponent
import com.banana.defacto.view.factDetails.FactDetailsPresenterImpl
import com.banana.defacto.view.factList.FactListPresenterImpl
import com.banana.defacto.view.main.MainPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class AppModule(
    private val modelComponent: ModelComponent
) {

    @Provides
    fun provideFactsManager(): FactsManager = modelComponent.factsManager()

    @Provides
    fun provideMainPresenter(factsManager: FactsManager): MainPresenterImpl {
        return MainPresenterImpl()
    }

    @Provides
    fun provideFactListPresenter(factsManager: FactsManager): FactListPresenterImpl {
        return FactListPresenterImpl(factsManager)
    }

    @Provides
    fun provideFactDetailsPresenter(): FactDetailsPresenterImpl {
        return FactDetailsPresenterImpl()
    }
}