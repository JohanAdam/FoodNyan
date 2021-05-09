package com.nyan.foodie.viewmodel.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyan.domain.state.DataState
import com.nyan.domain.usecases.test.PostTestBRUseCase
import com.nyan.foodie.event.Event
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import com.nyan.foodie.binding.model.restaurant.Restaurant as RestaurantBinding
import timber.log.Timber

class DetailsViewModel(private val data: RestaurantBinding, private val postUseCase: PostTestBRUseCase) : ViewModel() {

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

    //For testing purpose, we use post 400 response use case.
    fun loadMoreComment() {
        Timber.d("loadMoreComment: ")
        setStateEvent(DetailStateEvent.PostTestEvent)
    }

    private fun setStateEvent(detailStateEvent: DetailStateEvent) {
        Timber.d("setStateEvent: ")
        when(detailStateEvent) {
            is DetailStateEvent.PostTestEvent -> {
                postUseCase.execute()
                    .onEach { dataState ->
                        when(dataState) {
                            is DataState.Success -> {
                                _errorMsg.value = Event(dataState.data.status.toString())
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
            is DetailStateEvent.GetTestTrueEvent -> {

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}

sealed class DetailStateEvent {
    object PostTestEvent: DetailStateEvent()
    object GetTestTrueEvent: DetailStateEvent()
}