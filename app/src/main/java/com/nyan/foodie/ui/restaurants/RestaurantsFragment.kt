package com.nyan.foodie.ui.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nyan.domain.entity.restaurant.RestaurantEntity
import com.nyan.domain.state.DataState
import com.nyan.foodie.adapter.OnItemClickListener
import com.nyan.foodie.adapter.RestaurantsAdapter
import com.nyan.foodie.databinding.FragmentRestaurantsBinding
import com.nyan.foodie.ui.main.MainActivity
import com.nyan.foodie.viewmodel.restaurants.RestaurantsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

@ExperimentalCoroutinesApi
class RestaurantsFragment: Fragment() {

    private var _binding: FragmentRestaurantsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RestaurantsViewModel by viewModel()

    private lateinit var adapter: RestaurantsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRestaurantsBinding.inflate(inflater)
        val view = binding.root

        setupObserver()
        setupViewListener()

        return view
    }

    private fun setupObserver() {
        viewModel.listRestaurant.observe(viewLifecycleOwner, { dataState ->
            when(dataState) {
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
                is DataState.Success -> {
                    displayProgressBar(false)
                    displayData(dataState.data)
                }
                is DataState.Failed -> {
                    displayProgressBar(false)
                    showSnackBar(dataState.error.errorMsg)
                }
                else ->
                    displayProgressBar(false)
            }
        })
    }

    private fun setupViewListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getRestaurants()
        }

        binding.rvRestaurantList.layoutManager = LinearLayoutManager(context)
        adapter = RestaurantsAdapter(OnItemClickListener {
            showSnackBar(it.title)
        })
    }

    private fun displayProgressBar(isLoading: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = false
        binding.progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE

        if (isLoading) {
            adapter.submitList(null)
        }
    }

    private fun showSnackBar(errorMsg: String) {
        (activity as MainActivity).showSnackbar(errorMsg)
    }

    private fun displayData(data: List<RestaurantEntity>) {
        Timber.e("displayData: ${data.size}")
        binding.rvRestaurantList.adapter = adapter
        adapter.submitList(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}