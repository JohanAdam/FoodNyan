package com.nyan.domain.usecases

import com.nyan.domain.entity.RestaurantEntity
import com.nyan.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestaurantDetailsUseCase @Inject constructor(val repo: RemoteRepository) : FlowUseCase<RestaurantEntity> {
    override fun execute(): Flow<List<RestaurantEntity>> {
        TODO("Not yet implemented")
    }

}