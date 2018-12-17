package com.catalogit.di

import android.app.Application
import com.catalogit.view.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by gabeira@gmail.com on 17/12/18.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: Application)

    fun inject(app: MainActivity)
}