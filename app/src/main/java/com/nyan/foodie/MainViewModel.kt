package com.nyan.foodie

import androidx.lifecycle.*
import com.nyan.domain.entity.RestaurantEntity
import com.nyan.domain.repository.RemoteRepository
import com.nyan.domain.state.DataState
import com.nyan.domain.usecases.ListRestaurantsUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.subscribe
import kotlinx.coroutines.flow.subscribeOn
import kotlinx.coroutines.launch
import timber.log.Timber

sealed class MainStateEvent {
    object GetRestaurantsEvent: MainStateEvent()
}

class MainViewModel(
    private val listRestaurantUseCase: ListRestaurantsUseCase): ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<RestaurantEntity>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<RestaurantEntity>>> get() = _dataState

    init {
        getRestaurantList()
    }

    private fun getRestaurantList(){
        Timber.d("getRestaurantList: ")
        setStateEvent(MainStateEvent.GetRestaurantsEvent)
    }

    /**
     * Handle events that happen in this view model.
     */
    private fun setStateEvent(mainStateEvent: MainStateEvent) {
        Timber.d("setStateEvent: ")
        viewModelScope.launch {
            when(mainStateEvent) {
                is MainStateEvent.GetRestaurantsEvent -> {
                    Timber.d("setStateEvent: GetRestaurantEvent")
                    listRestaurantUseCase.execute()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
