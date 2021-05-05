package com.nyan.domain.usecases.test

import com.nyan.domain.entity.test.TestStatusEntity
import com.nyan.domain.repository.RemoteRepository
import com.nyan.domain.state.DataState
import kotlinx.coroutines.flow.Flow

class PostTestBRUseCase(private val remoteRepository: RemoteRepository) {

    fun execute(): Flow<DataState<TestStatusEntity>> {
        return remoteRepository.loadTestBR()
    }

}