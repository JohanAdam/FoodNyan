package com.nyan.foodie.ui.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nyan.foodie.databinding.FragmentRestaurantsBinding
import com.nyan.foodie.ui.details.DetailsFragment
import com.nyan.foodie.viewmodel.restaurants.RestaurantsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RestaurantsFragment: Fragment() {

    private val viewModel: RestaurantsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentRestaurantsBinding.inflate(inflater)

        viewModel.testNyan.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            this.findNavController().navigate(RestaurantsFragmentDirections.actionShowDetail())
        })

        return binding.root
    }

}