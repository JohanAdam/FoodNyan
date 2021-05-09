package com.nyan.foodie.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.material.snackbar.Snackbar
import com.nyan.domain.state.DataState
import com.nyan.foodie.R
import com.nyan.foodie.databinding.ActivityMainBinding
import com.nyan.foodie.dialog.DialogLoading
import com.nyan.foodie.viewmodel.main.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    private var dialogLoading: DialogLoading? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.restaurantDataState.observe(this, { dataState ->
            when(dataState) {
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
                is DataState.Success -> {
                    displayProgressBar(false)
//                    binding.tvTest.text = dataState.data.size.toString().plus(dataState.data[0].desciption)
                }
                is DataState.Failed -> {
                    displayProgressBar(false)
//                    binding.tvTest.text = dataState.error.errorMsg + " " + dataState.error.responseCode
                }
            }
        })

        viewModel.testTrueDataState.observe(this, { dataState ->
            when(dataState) {
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
                is DataState.Success -> {
                    displayProgressBar(false)
//                    binding.tvTest.text = dataState.data.status.toString()
                }
                is DataState.Failed -> {
                    displayProgressBar(false)
//                    binding.tvTest.text = dataState.error.errorMsg + " " + dataState.error.responseCode
                }
            }
        })
    }

    fun displayProgressBar(isLoading: Boolean) {
        if (isLoading) {
            dialogLoading = DialogLoading()
            dialogLoading?.showDialog(this,
                resources.getString(R.string.title_loading)
            ) {
                dialogLoading = null
            }
        } else {
            dialogLoading?.let {
                dialogLoading?.removeDialog()
            }
        }
    }

    fun showSnackbar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }

}