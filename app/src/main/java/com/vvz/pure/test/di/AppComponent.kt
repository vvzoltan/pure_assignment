package com.vvz.pure.test.di

import com.vvz.pure.test.MainActivity
import com.vvz.pure.test.di.dagger.ViewModelFactory
import dagger.Component

@Component(modules = [
    ViewModelModule::class,
    RetrofitModule::class,
    DataModule::class,
    RoomModule::class
])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun getViewModelFactory(): ViewModelFactory

}