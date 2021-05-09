package com.nyan.domain.usecases.restaurant

import com.nyan.domain.entity.restaurant.RestaurantEntity
import com.nyan.domain.repository.RemoteRepository
import com.nyan.domain.state.DataState
import kotlinx.coroutines.flow.Flow

class DetailsRestaurantUseCase(private val remoteRepository: RemoteRepository) {

    fun execute(): Flow<DataState<RestaurantEntity>>{
        TODO("Not yet implemented")
    }

}