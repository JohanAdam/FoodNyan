package com.nyan.domain.usecases.test

import com.nyan.domain.entity.test.TestStatusEntity
import com.nyan.domain.repository.RemoteRepository
import com.nyan.domain.state.DataState
import com.nyan.domain.usecases.FlowSingleUseCase
import kotlinx.coroutines.flow.Flow

class GetTestTrueUseCase(private val remoteRepository: RemoteRepository) :
    FlowSingleUseCase<TestStatusEntity> {

    override fun execute(): Flow<DataState<TestStatusEntity>> {
        return remoteRepository.loadTestTrue()
    }

}