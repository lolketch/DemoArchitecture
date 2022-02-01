package com.example.myapplication.di.modules

import androidx.lifecycle.ViewModel
import com.example.feature_list.ListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @[IntoMap ViewModelKey(ListViewModel::class)]
    fun provideDepartmentViewModel(mainViewModel: ListViewModel): ViewModel
}