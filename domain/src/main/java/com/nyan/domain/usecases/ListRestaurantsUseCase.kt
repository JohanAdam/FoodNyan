package com.nyan.domain.usecases

import com.nyan.domain.entity.RestaurantEntity
import com.nyan.domain.repository.RestaurantsRepository
import kotlinx.coroutines.flow.Flow

class ListRestaurantsUseCase(private val repository: RestaurantsRepository) {

    fun execute(): Flow<List<RestaurantEntity>> {
        return repository.loadRestaurants()
    }

}