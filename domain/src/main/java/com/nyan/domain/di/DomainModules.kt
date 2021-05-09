package com.nyan.domain.di

import com.nyan.domain.usecases.restaurant.DetailsRestaurantUseCase
import com.nyan.domain.usecases.restaurant.ListRestaurantsUseCase
import com.nyan.domain.usecases.test.PostTestBRUseCase
import com.nyan.domain.usecases.test.GetTestFalseUseCase
import com.nyan.domain.usecases.test.GetTestTrueUseCase
import org.koin.dsl.module

object DomainModules {

    val domainModule = module {
        factory { ListRestaurantsUseCase(remoteRepository = get()) }
        factory { DetailsRestaurantUseCase(remoteRepository = get()) }
        factory { GetTestTrueUseCase(remoteRepository = get()) }
        factory { GetTestFalseUseCase(remoteRepository = get()) }
        factory { PostTestBRUseCase(remoteRepository = get()) }
    }

}