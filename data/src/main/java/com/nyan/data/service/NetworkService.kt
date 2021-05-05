package com.nyan.data.service

import com.nyan.data.model.RestaurantDataModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface NetworkService {

    @GET("restaurant_dummy/5f554a9aaf64a72620742581350d40c7f100ac35/db.json")
    suspend fun getRestaurantList(): List<RestaurantDataModel>

}