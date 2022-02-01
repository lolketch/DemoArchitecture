package com.example.myapplication.di

import android.app.Application
import com.example.api.CharactersApi
import com.example.api.MultiViewModelFactory
import com.example.feature_list.CharactersDeps
import com.example.myapplication.di.modules.NetworkModule
import com.example.myapplication.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent : CharactersDeps {

    override val multiViewModelFactory: MultiViewModelFactory

    override val charactersApi: CharactersApi

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

@Module(includes = [NetworkModule::class, ViewModelModule::class])
class AppModule {
}

@Scope
annotation class AppScope