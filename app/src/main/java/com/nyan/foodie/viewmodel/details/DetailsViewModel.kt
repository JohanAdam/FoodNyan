package com.nyan.foodie.viewmodel.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyan.domain.state.DataState
import com.nyan.domain.usecases.test.GetTestFalseUseCase
import com.nyan.domain.usecases.test.GetTestTrueUseCase
import com.nyan.domain.usecases.test.PostTestBRUseCase
import com.nyan.foodie.event.Event
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import com.nyan.foodie.binding.model.restaurant.Restaurant as RestaurantBinding

class DetailsViewModel(
    private val data: RestaurantBinding,
    private val postUseCase: PostTestBRUseCase,
    private val getTUseCase: GetTestTrueUseCase,
    private val getFUseCase: GetTestFalseUseCase) : ViewModel() {

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
        setStateEvent(DetailStateEvent.PostTestEvent)
    }

    //No text will call the Test Api False & With text will send the Test Api True.
    fun sendMessage(msg: String) {
        if (msg.trim().isEmpty()) {
            setStateEvent(DetailStateEvent.GetFalseTrueEvent)
        } else {
            setStateEvent(DetailStateEvent.GetTestTrueEvent)
        }
    }

    fun navigate() {
        //TODO
        _errorMsg.value = Event("Opps, sorry. :(")
    }

    fun copy() {
        //TODO
        _errorMsg.value = Event("Opps, sorry. :(")
    }

    private fun setStateEvent(detailStateEvent: DetailStateEvent) {
        when(detailStateEvent) {
            is DetailStateEvent.PostTestEvent -> {
                postUseCase.execute()
                    .onEach { dataState ->
                        removeLoading()
                        when (dataState) {
                            is DataState.Success -> {
                                showToast(dataState.data.status.toString())
                            }
                            is DataState.Failed -> {
                                showToast(dataState.error.errorMsg)
                            }
                            is DataState.Loading -> {
                                showLoading()
                            }
                            else -> removeLoading()
                        }
                    }
                    .launchIn(viewModelScope)
            }
            is DetailStateEvent.GetTestTrueEvent -> {
                getTUseCase.execute()
                    .onEach { dataState ->
                        removeLoading()
                        when (dataState) {
                            is DataState.Success -> {
                                showToast(dataState.data.status.toString())
                            }
                            is DataState.Failed -> {
                                showToast(dataState.error.errorMsg)
                            }
                            is DataState.Loading -> {
                                showLoading()
                            }
                            else -> removeLoading()
                        }
                    }
                    .launchIn(viewModelScope)
            }
            is DetailStateEvent.GetFalseTrueEvent -> {
                getFUseCase.execute()
                    .onEach { dataState ->
                        removeLoading()
                        when (dataState) {
                            is DataState.Success -> {
                                showToast(dataState.data.status.toString())
                            }
                            is DataState.Failed -> {
                                showToast(dataState.error.errorMsg)
                            }
                            is DataState.Loading -> {
                                showLoading()
                            }
                            else -> removeLoading()
                        }
                    }
                    .launchIn(viewModelScope)
            }
        }
    }

    private fun showLoading() {
        _isLoading.value = Event(true)
    }

    private fun removeLoading() {
        _isLoading.value = Event(false)
    }

    private fun showToast(msg: String) {
        _errorMsg.value = Event(msg)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}

sealed class DetailStateEvent {
    object PostTestEvent: DetailStateEvent()
    object GetTestTrueEvent: DetailStateEvent()
    object GetFalseTrueEvent: DetailStateEvent()
}