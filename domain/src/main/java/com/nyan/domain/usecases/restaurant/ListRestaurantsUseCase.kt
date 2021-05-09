package com.nyan.domain.usecases.restaurant

import com.nyan.domain.entity.restaurant.RestaurantEntity
import com.nyan.domain.repository.RemoteRepository
import com.nyan.domain.state.DataState
import kotlinx.coroutines.flow.Flow

class ListRestaurantsUseCase(private val remoteRepository: RemoteRepository) {

    fun execute(): Flow<DataState<List<RestaurantEntity>>> {
        return remoteRepository.loadRestaurants()
    }

}