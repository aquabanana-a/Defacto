package com.banana.defacto.repo.di

import android.content.Context
import com.banana.defacto.api.NumbersApiService
import com.banana.defacto.api.NumbersApiServiceImpl
import com.banana.defacto.repo.FactsRepository
import com.banana.defacto.repo.FactsRepositoryImpl
import com.banana.defacto.repo.local.FactsLocalDataStore
import com.banana.defacto.repo.local.FactsLocalDataStoreImpl
import com.banana.defacto.repo.remote.FactsRemoteDataStore
import com.banana.defacto.repo.remote.FactsRemoteDataStoreImpl
import com.banana.defacto.db.FactsDb
import com.banana.defacto.db.FactsDbImpl
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

    @Provides
    fun provideNumbersApiService(): NumbersApiService {
        return NumbersApiServiceImpl()
    }

    @Provides
    fun provideFactsDb(context: Context): FactsDb {
        return FactsDbImpl(context)
    }

    @Provides
    fun provideFactsLocalDataStore(factsDb: FactsDb): FactsLocalDataStore {
        return FactsLocalDataStoreImpl(factsDb)
    }

    @Provides
    fun provideFactsRemoteDataStore(numbersApiService: NumbersApiService): FactsRemoteDataStore {
        return FactsRemoteDataStoreImpl(numbersApiService)
    }

    @Provides
    fun provideFactsRepository(localDataStore: FactsLocalDataStore, remoteDataStore: FactsRemoteDataStore): FactsRepository {
        return FactsRepositoryImpl(localDataStore, remoteDataStore)
    }
}