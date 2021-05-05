package com.nyan.domain.usecases.restaurant

import com.nyan.domain.entity.restaurant.RestaurantEntity
import com.nyan.domain.repository.RemoteRepository
import com.nyan.domain.state.DataState
import com.nyan.domain.usecases.FlowSingleUseCase
import kotlinx.coroutines.flow.Flow

class DetailsRestaurantUseCase(private val remoteRepository: RemoteRepository) :
    FlowSingleUseCase<RestaurantEntity> {

    override fun execute(): Flow<DataState<RestaurantEntity>>{
        TODO("Not yet implemented")
    }

}