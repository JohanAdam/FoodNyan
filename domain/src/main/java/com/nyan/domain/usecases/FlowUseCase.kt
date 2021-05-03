package com.nyan.domain.usecases

import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

/**
 * Base class for Flow Use Case.
 */
interface FlowUseCase<R> {
    fun execute(): Flow<List<R>>
}