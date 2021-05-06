package com.nyan.foodie.viewmodel.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RestaurantsViewModel: ViewModel() {

    private var _testNyan: MutableLiveData<String> = MutableLiveData()
    val testNyan: LiveData<String> get() = _testNyan


    fun poop(text: String) {
        _testNyan.postValue(text)
    }

}