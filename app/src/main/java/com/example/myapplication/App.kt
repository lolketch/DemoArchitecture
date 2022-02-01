package com.example.myapplication

import android.app.Application
import com.example.feature_list.CharactersDepsStore
import com.example.myapplication.di.AppComponent
import com.example.myapplication.di.DaggerAppComponent

class App : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        CharactersDepsStore.deps = appComponent
    }
}