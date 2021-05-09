package com.nyan.foodie.di

import com.nyan.domain.entity.restaurant.RestaurantEntity
import com.nyan.foodie.binding.model.restaurant.Restaurant as RestaurantBinding
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
            RestaurantsViewModel(get())
        }
        viewModel {
            (data: RestaurantBinding) -> DetailsViewModel(data, get())
        }
    }

}