package com.nyan.data.repository

import com.nyan.data.mapper.RestaurantMapper
import com.nyan.data.service.NetworkService
import com.nyan.domain.state.DataState
import com.nyan.domain.entity.RestaurantEntity
import com.nyan.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class RemoteRepositoryImpl(
    private val networkService: NetworkService,
    private val mapper: RestaurantMapper) : RemoteRepository {

    override fun loadRestaurants(): Flow<DataState<List<RestaurantEntity>>> = flow {
        //Return Loading.
        emit(DataState.Loading)

        try {
            //Get list from API.
            val restaurantNetList = networkService.getRestaurantList()

            //Mapping network model to domain model.
            val restaurantList = mapper.mapDataToEntityList(restaurantNetList)

            //Return result.
            emit(DataState.Success(restaurantList))
        } catch (e: Exception) {
            e.printStackTrace()
            //Return error.
            emit(DataState.Failed(e))
        }

    }

    override fun loadRestaurantDetails(restaurantId: String): Flow<DataState<RestaurantEntity>> {
        TODO("Not yet implemented")
    }

}