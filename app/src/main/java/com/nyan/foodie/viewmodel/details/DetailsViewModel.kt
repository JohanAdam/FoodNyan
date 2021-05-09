package com.nyan.foodie.viewmodel.details

import androidx.lifecycle.ViewModel
import com.nyan.foodie.binding.model.restaurant.Restaurant as RestaurantBinding
import timber.log.Timber

class DetailsViewModel(data: RestaurantBinding) : ViewModel() {

    init {
        Timber.e("Value receive is ${data.title}")
    }

    fun GGWP() {
        Timber.e("GGWP: WOHOO!")
    }

}