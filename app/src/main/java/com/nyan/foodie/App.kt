package com.nyan.foodie

import android.app.Application
import android.app.Presentation
import android.util.Log
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
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        //Initialized timber.
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }

        startKoin {
            androidContext(this@App)
            modules(listOf(dataModule, networkModule, repositoryModule, domainModule, presentationModule))
        }
    }

    private class ReleaseTree : Timber.Tree() {
        override fun log(
            priority: Int, tag: String?, message: String,
            t: Throwable?
        ) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }

            // log your crash to your favourite
            // Sending crash report to Firebase CrashAnalytics

            // FirebaseCrash.report(message);
            // FirebaseCrash.report(new Exception(message));
        }
    }

}