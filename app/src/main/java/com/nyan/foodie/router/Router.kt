package com.nyan.foodie.router

import com.nyan.foodie.binding.model.restaurant.Restaurant as RestaurantBinding

interface Router {
    fun showRestaurants()
    fun showDetails(restaurant: RestaurantBinding)
    fun back()
    fun navigationUp(): Boolean
    fun isInRootScreen(): Boolean
}