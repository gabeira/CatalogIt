package com.catalogit

import android.app.Application
import com.catalogit.di.AppComponent
import com.catalogit.di.AppModule
import com.catalogit.di.DaggerAppComponent

class MyApp: Application() {


    val component: AppComponent? by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}