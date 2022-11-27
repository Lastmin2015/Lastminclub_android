package ru.vsibi.presentation.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.vsibi.data.di.RemoteModule
import ru.vsibi.data.di.RepositoryModule
import ru.vsibi.data.di.StorageModule
import ru.vsibi.presentation.screens.main.MainFragment

@Component(modules = [StorageModule::class, RemoteModule::class, RepositoryModule::class])
interface PresentationComponent {

    fun inject(fragment: MainFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun build() : PresentationComponent
    }
}