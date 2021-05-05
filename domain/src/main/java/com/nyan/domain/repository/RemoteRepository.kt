package com.nyan.domain.repository

import com.nyan.domain.entity.RestaurantEntity
import com.nyan.domain.state.DataState
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {

    fun loadRestaurants(): Flow<DataState<List<RestaurantEntity>>>

    fun loadRestaurantDetails(restaurantId: String): Flow<DataState<RestaurantEntity>>

}