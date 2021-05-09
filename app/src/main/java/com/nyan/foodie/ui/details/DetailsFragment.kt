package com.nyan.foodie.ui.details

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.nyan.foodie.R
import com.nyan.foodie.adapter.CommentsAdapter
import com.nyan.foodie.adapter.PicturesAdapter
import com.nyan.foodie.binding.model.restaurant.Restaurant as RestaurantBinding
import com.nyan.foodie.databinding.FragmentDetailsBinding
import com.nyan.foodie.dialog.DialogLoading
import com.nyan.foodie.event.EventObserver
import com.nyan.foodie.ui.main.MainActivity
import com.nyan.foodie.viewmodel.details.DetailsViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class DetailsFragment: Fragment() {

    private val viewModel: DetailsViewModel by viewModel {
        parametersOf(arguments?.getParcelable<RestaurantBinding>(RESTAURANT_DATA))
    }
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val picturesAdapter by lazy {
        PicturesAdapter { url ->
            //TODO
        }
    }

    private val commentsAdapter by lazy {
        CommentsAdapter { url , isLoading ->
            if (isLoading) {
                viewModel.loadMoreComment()
            } else {
                url?.let{
                    showSnackbar("Click Click! ;)")
                }
            }
        }
    }

    companion object {
        val RESTAURANT_DATA : String = "data"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater)
        val view = binding.root
        binding.lifecycleOwner = this

        //Received the data sent by previous page.
        val restaurant = DetailsFragmentArgs.fromBundle(requireArguments()).selectedRestaurant
        arguments = bundleOf(RESTAURANT_DATA to restaurant)

        setupObserver()
        setupView();

        return view
    }

    private fun setupView() {
        binding.layoutHeader.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.rvPictures.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPictures.itemAnimator = DefaultItemAnimator()

        binding.rvComments.layoutManager = LinearLayoutManager(context)
        binding.rvComments.itemAnimator = DefaultItemAnimator()
    }

    private fun setupObserver() {
        viewModel.restaurant.observe(viewLifecycleOwner, {
            displayData(it)
        })

        viewModel.errorMsg.observe(viewLifecycleOwner, EventObserver {
            showSnackbar(it)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, EventObserver {
            Timber.e("setupObserver: isLoading $it")
            showProgressBar(it)
        })
    }

    private fun showProgressBar(isLoading: Boolean) {
        Timber.e("showProgressBar: $isLoading")
        val mainActivity = activity as MainActivity
        if (isLoading) {
            mainActivity.displayProgressBar(true)
        } else {
            mainActivity.displayProgressBar(false)
        }
    }

    private fun showSnackbar(msg: String) {
        (activity as MainActivity).showSnackbar(msg)
    }

    private fun displayData(restaurant: RestaurantBinding?) {
        Timber.e("displayData: ${restaurant!!.pictures.size}")
        binding.data = restaurant

        //Bind picture list to rv.
        binding.rvPictures.adapter = picturesAdapter
        picturesAdapter.submitList(restaurant.pictures)

        //Bind comment list to rv.
        binding.rvComments.adapter = commentsAdapter
        commentsAdapter.submitList(restaurant.comments.take(3))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}