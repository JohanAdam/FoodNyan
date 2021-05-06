package com.nyan.foodie.viewmodel.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyan.domain.entity.restaurant.RestaurantEntity
import com.nyan.domain.state.DataState
import com.nyan.domain.usecases.restaurant.ListRestaurantsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

sealed class RestaurantsStateEvent {
    object GetRestaurantEvent: RestaurantsStateEvent()
}

@ExperimentalCoroutinesApi
class RestaurantsViewModel(
    private val listRestaurantsUseCase: ListRestaurantsUseCase): ViewModel() {

    private val _listRestaurant: MutableLiveData<DataState<List<RestaurantEntity>>> = MutableLiveData()
    val listRestaurant: LiveData<DataState<List<RestaurantEntity>>> get() = _listRestaurant

    init {
        getRestaurants()
    }

    fun getRestaurants() {
        Timber.d("getRestaurants: ")
        setStateEvent(RestaurantsStateEvent.GetRestaurantEvent)
    }

    private fun setStateEvent(event: RestaurantsStateEvent) {
        Timber.d("setStateEvent: ")
        viewModelScope.launch {
            when(event) {
                is RestaurantsStateEvent.GetRestaurantEvent -> {
                    Timber.d("setStateEvent: GetRestaurantEvent")
                    listRestaurantsUseCase.execute()
                        .onEach { dataState ->
                            _listRestaurant.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        //TODO Yep, I aware about this, and find a better solution for this.
        viewModelScope.cancel()
    }

}