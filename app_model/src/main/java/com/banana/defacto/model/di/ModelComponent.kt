package com.banana.defacto.model.di

import com.banana.defacto.model.FactsManager
import com.banana.defacto.repo.di.RepoComponent
import dagger.Component

@Component(
    modules = [ModelModule::class],
    dependencies = [RepoComponent::class]
)
interface ModelComponent {

    fun factsManager(): FactsManager
}