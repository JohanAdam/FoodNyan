package com.nyan.foodie.di

import com.nyan.foodie.viewmodel.details.DetailsViewModel
import com.nyan.foodie.viewmodel.main.MainViewModel
import com.nyan.foodie.viewmodel.restaurants.RestaurantsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {

    val presentationModule = module {
        viewModel {
            MainViewModel(get(), get())
        }
        viewModel {
            RestaurantsViewModel()
        }
        viewModel {
            DetailsViewModel()
        }
    }

}