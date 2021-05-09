package com.nyan.foodie.di

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.nyan.foodie.router.AppRouter
import com.nyan.foodie.router.Router
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object AppModule {

    val appModule = module {
        factory { (activity: FragmentActivity) ->
            AppRouter(activity) as Router
        }
    }

}