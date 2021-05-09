package com.nyan.foodie.router

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.nyan.foodie.R
import com.nyan.foodie.binding.model.restaurant.Restaurant as RestaurantBinding
import com.nyan.foodie.ui.restaurants.RestaurantsFragmentDirections


class AppRouter(
    activity: FragmentActivity) : Router {

    private val navController: NavController by lazy {
        Navigation.findNavController(activity, R.id.nav_host_fragment)
    }

    //If there is root screen , can added here.
    private val rootScreens = setOf(R.id.restaurantsFragment)

    init {
        val appBarConfiguration = AppBarConfiguration.Builder(rootScreens).build()
        if (activity is AppCompatActivity) {
            NavigationUI.setupActionBarWithNavController(activity, navController, appBarConfiguration)
        }
    }

    override fun showRestaurants() {
        navController.navigate(R.id.restaurantsFragment)
    }

    override fun showDetails(restaurant: RestaurantBinding) {
        navController.navigate(RestaurantsFragmentDirections.actionShowDetail(restaurant))
    }

    override fun back() {
        navController.popBackStack()
    }

    override fun navigationUp(): Boolean {
        return navController.navigateUp()
    }

    override fun isInRootScreen(): Boolean {
        return rootScreens.contains(navController.currentDestination?.id)
    }

}