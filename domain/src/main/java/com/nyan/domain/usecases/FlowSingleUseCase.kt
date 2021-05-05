package com.nyan.domain.usecases

import com.nyan.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface FlowSingleUseCase<R> {
    fun execute(): Flow<DataState<R>>
}