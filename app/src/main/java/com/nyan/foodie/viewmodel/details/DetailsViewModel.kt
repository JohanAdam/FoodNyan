package com.nyan.foodie.viewmodel.details

import androidx.lifecycle.ViewModel
import com.nyan.domain.entity.restaurant.RestaurantEntity
import timber.log.Timber

class DetailsViewModel(data: RestaurantEntity) : ViewModel() {

    init {
        Timber.e("Value receive is ${data.title}")
    }

    fun GGWP() {
        Timber.e("GGWP: WOHOO!")
    }

}