package com.nyan.domain.repository

import com.nyan.domain.entity.RestaurantEntity
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {

    fun loadRestaurants(): Flow<List<RestaurantEntity>>

    fun loadRestaurantDetails(restaurantId: String): Single<RestaurantEntity>

}