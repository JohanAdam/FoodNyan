package com.nyan.data.repository

import com.nyan.data.mapper.RestaurantMapper
import com.nyan.data.mapper.TestStatusMapper
import com.nyan.data.model.test.TestStatusDataModel
import com.nyan.data.service.NetworkService
import com.nyan.data.service.NetworkService.Companion.RESTAURANT_BASE_URL
import com.nyan.domain.state.DataState
import com.nyan.domain.entity.restaurant.RestaurantEntity
import com.nyan.domain.entity.test.TestStatusEntity
import com.nyan.domain.repository.RemoteRepository
import com.nyan.domain.network.NetworkError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.lang.Exception

class RemoteRepositoryImpl(
    private val networkService: NetworkService,
    private val restaurantMapper: RestaurantMapper,
    private val testStatusMapper: TestStatusMapper) : RemoteRepository {

    override fun loadTestTrue(): Flow<DataState<TestStatusEntity>> = flow {
        try {
            processTestStatus(networkService.getTestTrue())
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(DataState.Failed(NetworkError(e)))
        }
    }

    override fun loadTestFalse(): Flow<DataState<TestStatusEntity>> = flow {
        try {
            processTestStatus(networkService.getTestFalse())
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(DataState.Failed(NetworkError(e)))
        }
    }

    override fun loadTestBR(): Flow<DataState<TestStatusEntity>> = flow {
        try {
            processTestStatus(networkService.getTestBR())
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Failed(NetworkError(e)))
        }
    }

    private suspend fun FlowCollector<DataState<TestStatusEntity>>.processTestStatus(testApi: TestStatusDataModel) {
        //Return Loading.
        emit(DataState.Loading)

        //Get data from API.
        val testStatusNet = testApi

        //Mapping network model to domain model.
        val testStatus = testStatusMapper.mapToEntity(testStatusNet)

        //Return result.
        emit(DataState.Success(testStatus))
    }

    override fun loadRestaurants(): Flow<DataState<List<RestaurantEntity>>> = flow {
        //Return Loading.
        emit(DataState.Loading)

        try {
            //Get list from API.
            val restaurantNetList = networkService.getRestaurantList(RESTAURANT_BASE_URL)

            //Mapping network model to domain model.
            val restaurantList = restaurantMapper.mapDataToEntityList(restaurantNetList)

            //Return result.
            emit(DataState.Success(restaurantList))
        } catch (e: HttpException) {
            e.printStackTrace()
            //Return error.
            emit(DataState.Failed(NetworkError(e)))
        }

    }

    override fun loadRestaurantDetails(restaurantId: String): Flow<DataState<RestaurantEntity>> {
        TODO("Not yet implemented")
    }

}