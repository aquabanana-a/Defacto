package com.banana.defacto.repo.di

import android.content.Context
import com.banana.defacto.repo.FactsRepository
import dagger.BindsInstance
import dagger.Component

@Component(modules = [RepoModule::class])
interface RepoComponent {
    fun factsRepository(): FactsRepository

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): RepoComponent
    }
}