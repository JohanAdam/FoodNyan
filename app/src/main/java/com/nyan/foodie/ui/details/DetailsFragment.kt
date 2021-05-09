package com.nyan.foodie.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.nyan.foodie.binding.model.restaurant.Restaurant as RestaurantBinding
import com.nyan.foodie.databinding.FragmentDetailsBinding
import com.nyan.foodie.viewmodel.details.DetailsViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailsFragment: Fragment() {

    private val viewModel: DetailsViewModel by viewModel {
        parametersOf(arguments?.getParcelable<RestaurantBinding>("data"))
    }
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater)

        val restaurant = DetailsFragmentArgs.fromBundle(requireArguments()).selectedRestaurant
        arguments = bundleOf("data" to restaurant)

        viewModel.GGWP()

        return binding.root
    }

}