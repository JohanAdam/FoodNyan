package com.nyan.domain.repository

import com.nyan.domain.entity.restaurant.RestaurantEntity
import com.nyan.domain.entity.test.TestStatusEntity
import com.nyan.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {

    fun loadTestTrue(): Flow<DataState<TestStatusEntity>>

    fun loadTestFalse(): Flow<DataState<TestStatusEntity>>

    fun loadTestBR(): Flow<DataState<TestStatusEntity>>

    fun loadRestaurants(): Flow<DataState<List<RestaurantEntity>>>

    fun loadRestaurantDetails(restaurantId: String): Flow<DataState<RestaurantEntity>>

}