package com.nyan.domain.usecases

import com.nyan.domain.entity.RestaurantEntity
import com.nyan.domain.repository.RemoteRepository
import com.nyan.domain.state.DataState
import kotlinx.coroutines.flow.Flow

class DetailsRestaurantUseCase(private val remoteRepository: RemoteRepository) : FlowSingleUseCase<RestaurantEntity> {

    override fun execute(): Flow<DataState<RestaurantEntity>>{
        TODO("Not yet implemented")
    }

}