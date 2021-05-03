package com.nyan.domain.usecases

import com.nyan.domain.entity.RestaurantEntity
import com.nyan.domain.repository.RestaurantsRepository
import kotlinx.coroutines.flow.Flow

class RestaurantDetailsUseCase(private val repository: RestaurantsRepository) {

    fun execute(restaurantId: String): Flow<RestaurantEntity> {
        return repository.loadRestaurantDetails(restaurantId)
    }

}