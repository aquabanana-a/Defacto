package com.banana.defacto.model.di

import com.banana.defacto.repo.FactsRepository
import com.banana.defacto.model.FactsManager
import com.banana.defacto.model.FactsManagerImpl
import dagger.Module
import dagger.Provides

@Module
class ModelModule {

    @Provides
    fun provideFactsManager(repository: FactsRepository): FactsManager {
        return FactsManagerImpl(repository)
    }
}