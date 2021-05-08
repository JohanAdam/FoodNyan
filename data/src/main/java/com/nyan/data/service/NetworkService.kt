package com.nyan.data.service

import com.nyan.data.model.restaurant.RestaurantDataModel
import com.nyan.data.model.test.TestStatusDataModel
import com.nyan.domain.entity.test.TestStatusEntity
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface NetworkService {

    companion object {
        const val BASE_URL : String = "http://apitest.thelorry.com/"
        const val RESTAURANT_BASE_URL : String = "https://raw.githubusercontent.com/JohanAdam/restaurant_dummy/master/db.json"
    }

    @GET("/test/true")
    suspend fun getTestTrue(): TestStatusDataModel

    @GET("/test/false")
    suspend fun getTestFalse(): TestStatusDataModel

    @POST("/test/400")
    suspend fun getTestBR(): TestStatusDataModel

    @GET
    suspend fun getRestaurantList(@Url url: String): List<RestaurantDataModel>

}