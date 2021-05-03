package com.nyan.domain.repository

import com.nyan.domain.entity.RestaurantEntity
import kotlinx.coroutines.flow.Flow

interface RestaurantsRepository {

    fun loadRestaurants(): Flow<List<RestaurantEntity>>

    fun loadRestaurantDetails(restaurantId: String): Flow<RestaurantEntity>

}