package com.nyan.data.di

import com.nyan.data.mapper.RestaurantMapper
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DataModules {
    
    val dataModule = module {
        factory { RestaurantMapper() }
    }

}