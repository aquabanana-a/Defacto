package com.banana.defacto.model.di

import android.content.Context
import com.banana.defacto.repo.di.DaggerRepoComponent

object ModelGraph {
    fun create(context: Context): ModelComponent {
        val repoComponent = DaggerRepoComponent.factory().create(context)
        return DaggerModelComponent.builder()
            .repoComponent(repoComponent)
            .build()
    }
}