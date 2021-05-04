package com.nyan.foodie.di

import com.nyan.foodie.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object PresentationModule {

    val presentationModule = module {
        viewModel {
            MainViewModel(get())
        }
    }

}