package com.nyan.domain.di

import com.nyan.domain.usecases.DetailsRestaurantUseCase
import com.nyan.domain.usecases.ListRestaurantsUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DomainModules {

    val domainModule = module {
        factory { ListRestaurantsUseCase(remoteRepository = get()) }
        factory { DetailsRestaurantUseCase(remoteRepository = get()) }
    }

}