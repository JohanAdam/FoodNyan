package com.nyan.data.repository

import com.nyan.data.mapper.RestaurantMapper
import com.nyan.data.service.NetworkService
import com.nyan.domain.entity.RestaurantEntity
import com.nyan.domain.repository.RemoteRepository
import dagger.Lazy
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val mapper: Lazy<RestaurantMapper>) : RemoteRepository {

    override fun loadRestaurants(): Flow<List<RestaurantEntity>> {
        return networkService.getRestaurantList().map {
            mapper.get().mapDataToEntityList(it)
        }
    }

    override fun loadRestaurantDetails(restaurantId: String): Single<RestaurantEntity> {
        TODO("Not yet implemented")
    }

}