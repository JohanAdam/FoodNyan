package com.nyan.foodie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.nyan.domain.repository.RemoteRepository
import com.nyan.domain.usecases.ListRestaurantsUseCase

class MainViewModel(private val restaurantsUseCase: ListRestaurantsUseCase): ViewModel() {

    private val _testValue: MutableLiveData<Int> = MutableLiveData()
    val testValue: LiveData<Int> get() = _testValue

    private var count = 0

    fun pressBro() {
        _testValue.postValue(count++)
    }
}
