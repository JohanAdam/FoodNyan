package com.nyan.foodie

import androidx.lifecycle.*
import com.nyan.domain.entity.restaurant.RestaurantEntity
import com.nyan.domain.entity.test.TestStatusEntity
import com.nyan.domain.state.DataState
import com.nyan.domain.usecases.restaurant.ListRestaurantsUseCase
import com.nyan.domain.usecases.test.GetTestTrueUseCase
import com.nyan.domain.usecases.test.PostTestBRUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

sealed class MainStateEvent {
    object GetRestaurantsEvent: MainStateEvent()
    object GetTestTrueEvent: MainStateEvent()
}

class MainViewModel(
    private val listRestaurantUseCase: ListRestaurantsUseCase,
    private val getTestTrueUseCase: PostTestBRUseCase
): ViewModel() {

    private val _restaurantDataState: MutableLiveData<DataState<List<RestaurantEntity>>> = MutableLiveData()
    val restaurantDataState: LiveData<DataState<List<RestaurantEntity>>> get() = _restaurantDataState

    private val _testTrueDataState: MutableLiveData<DataState<TestStatusEntity>> = MutableLiveData()
    val testTrueDataState: LiveData<DataState<TestStatusEntity>> get() = _testTrueDataState

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
                            _restaurantDataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.GetTestTrueEvent -> {
                    Timber.e("setStateEvent: GetTestTrueEvent")
                    getTestTrueUseCase.execute()
                        .onEach { dataState ->
                            _testTrueDataState.value = dataState
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
