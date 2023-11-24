package com.vvz.pure.test

import android.app.Application
import com.vvz.pure.test.di.AppComponent
import com.vvz.pure.test.di.DaggerAppComponent


class PureApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
        appComponent = DaggerAppComponent.create()
    }

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var application: Application
    }

}