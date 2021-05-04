package com.nyan.foodie

import android.app.Application
import android.app.Presentation
import com.nyan.data.di.DataModules
import com.nyan.data.di.DataModules.dataModule
import com.nyan.data.di.NetworkModules
import com.nyan.data.di.NetworkModules.networkModule
import com.nyan.data.di.RepositoryModule
import com.nyan.data.di.RepositoryModule.repositoryModule
import com.nyan.domain.di.DomainModules
import com.nyan.domain.di.DomainModules.domainModule
import com.nyan.foodie.di.PresentationModule
import com.nyan.foodie.di.PresentationModule.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(dataModule, networkModule, repositoryModule, domainModule, presentationModule))
        }
    }


}