package com.nyan.domain.usecases

import com.nyan.domain.state.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Base class for Flow Use Case.
 */
interface FlowListUseCase<R> {
    fun execute(): Flow<DataState<List<R>>>
}