package com.nyan.foodie.viewmodel.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyan.domain.state.DataState
import com.nyan.domain.usecases.restaurant.ListRestaurantsUseCase
import com.nyan.foodie.binding.model.restaurant.RestaurantConverter
import com.nyan.foodie.binding.model.restaurant.Restaurant as RestaurantBinding
import com.nyan.foodie.event.Event
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

sealed class RestaurantsStateEvent {
    object GetRestaurantEvent: RestaurantsStateEvent()
}

@ExperimentalCoroutinesApi
class RestaurantsViewModel(
    private val listRestaurantsUseCase: ListRestaurantsUseCase): ViewModel() {

    private val _navigateToRestaurantDetails: MutableLiveData<Event<RestaurantBinding>> = MutableLiveData()
    val navigateToRestaurantDetails: LiveData<Event<RestaurantBinding>> get() = _navigateToRestaurantDetails

    private val _isLoading: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val isLoading: LiveData<Event<Boolean>> get() = _isLoading

    private val _errorMsg: MutableLiveData<Event<String>> = MutableLiveData()
    val errorMsg: LiveData<Event<String>> get() = _errorMsg

    private val _listRestaurant: MutableLiveData<List<RestaurantBinding>> = MutableLiveData()
    val listRestaurant: LiveData<List<RestaurantBinding>> get() = _listRestaurant

    private val _listTag: MutableLiveData<List<String>> = MutableLiveData()
    val listTag: LiveData<List<String>> get() = _listTag

    init {
        getTags()
        getRestaurants()
    }

    // For now load hardcode tags.
    private fun getTags() {
        //Load tags.
        val listTags = arrayListOf("Asian", "Western", "Japanese", "Cat Food", "Fast Food", "Fish Food")

        //Send tags to Ui.
        _listTag.value = listTags
    }

    fun getRestaurants() {
        setStateEvent(RestaurantsStateEvent.GetRestaurantEvent)
    }

    private fun setStateEvent(event: RestaurantsStateEvent) {
        viewModelScope.launch {
            when(event) {
                is RestaurantsStateEvent.GetRestaurantEvent -> {
                    listRestaurantsUseCase.execute()
                        .onEach { dataState ->
                            _isLoading.value = Event(false)
                            when (dataState) {
                                is DataState.Success -> {
                                    _listRestaurant.value = RestaurantConverter.fromEntityToBinding(dataState.data)
                                }
                                is DataState.Failed -> {
                                    _errorMsg.value = Event(dataState.error.errorMsg)
                                }
                                is DataState.Loading -> {
                                    _isLoading.value = Event(true)
                                }
                                else -> _isLoading.value = Event(false)
                            }

                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }

    fun openRestaurantDetail(item: RestaurantBinding) {
        _navigateToRestaurantDetails.value = (Event(item))
    }

    fun selectFilter() {
        _errorMsg.value = Event("Opps, sorry. :(")
    }

    fun selectTag(tag: String) {
        _errorMsg.value = Event("Selected ".plus(tag))
    }

    override fun onCleared() {
        super.onCleared()
        //TODO Yep, I aware about this, and find a better solution for this.
        viewModelScope.cancel()
    }

}