package com.nyan.foodie

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel

@ViewModelInject
constructor(@Assisted val handle: SavedStateHandle): ViewModel() {

    private val _testValue: MutableLiveData<Int> = MutableLiveData()
    val testValue: LiveData<Int> get() = _testValue

    private var count = 0

    fun pressBro() {
        _testValue.postValue(count++)
    }
}
