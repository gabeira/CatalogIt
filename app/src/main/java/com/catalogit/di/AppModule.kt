package com.catalogit.di

import android.app.Application
import android.content.Context
import com.catalogit.MyApp
import com.catalogit.data.CatalogDataRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Singleton

/**
 * Created by gabeira@gmail.com on 17/12/18.
 */
@Module
open class AppModule(private val app: Application) {

    @Provides
    fun provideContext(application: MyApp): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideApplication() = app

    @Provides
    @Singleton
    fun provideRepository(): CatalogDataRepository {
        return CatalogDataRepository(Job() + Dispatchers.Main)
    }
}