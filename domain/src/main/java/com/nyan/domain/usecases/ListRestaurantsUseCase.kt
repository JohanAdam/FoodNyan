package com.nyan.domain.usecases

import com.nyan.domain.entity.RestaurantEntity
import com.nyan.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListRestaurantsUseCase @Inject constructor(val remoteRepository: RemoteRepository) : FlowUseCase<RestaurantEntity> {

    override fun execute(): Flow<List<RestaurantEntity>> {
        return remoteRepository.loadRestaurants()
    }

}