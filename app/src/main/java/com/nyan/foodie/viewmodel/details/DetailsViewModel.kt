package com.nyan.foodie.viewmodel.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nyan.foodie.event.Event
import com.nyan.foodie.binding.model.restaurant.Restaurant as RestaurantBinding
import timber.log.Timber

class DetailsViewModel(data: RestaurantBinding) : ViewModel() {

    private val _restaurant: MutableLiveData<RestaurantBinding> = MutableLiveData()
    val restaurant: LiveData<RestaurantBinding> get() = _restaurant

    private val _isLoading: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val isLoading: LiveData<Event<Boolean>> get() = _isLoading

    private val _errorMsg: MutableLiveData<Event<String>> = MutableLiveData()
    val errorMsg: LiveData<Event<String>> get() = _errorMsg

    init {
        //Supposed to sent id to API, send get the detail result. But for simplify of the test, I just passed here all the data.
        _restaurant.value = data
    }

}