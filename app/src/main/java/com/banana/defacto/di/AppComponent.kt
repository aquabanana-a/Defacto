package com.banana.defacto.di

import com.banana.defacto.view.factDetails.FactDetailsFragment
import com.banana.defacto.view.factList.FactListFragment
import com.banana.defacto.view.main.MainActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: FactListFragment)
    fun inject(fragment: FactDetailsFragment)

    @Component.Builder
    interface Builder {
        fun appModule(module: AppModule): Builder
        fun build(): AppComponent
    }
}