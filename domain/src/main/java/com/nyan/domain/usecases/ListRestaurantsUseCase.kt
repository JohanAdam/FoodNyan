package com.nyan.domain.usecases

import com.nyan.domain.entity.RestaurantEntity
import com.nyan.domain.repository.RemoteRepository
import com.nyan.domain.state.DataState
import kotlinx.coroutines.flow.Flow

class ListRestaurantsUseCase(private val remoteRepository: RemoteRepository) : FlowListUseCase<RestaurantEntity> {

    override fun execute(): Flow<DataState<List<RestaurantEntity>>> {
        return remoteRepository.loadRestaurants()
    }

}