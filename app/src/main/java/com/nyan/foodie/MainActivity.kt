package com.nyan.foodie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.nyan.domain.state.DataState
import com.nyan.foodie.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.dataState.observe(this, { dataState ->
            when(dataState) {
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
                is DataState.Success -> {
                    displayProgressBar(false)
                    binding.tvTest.text = dataState.data.size.toString().plus(dataState.data[0].desciption)
                }
                is DataState.Failed -> {
                    displayProgressBar(false)
                    binding.tvTest.text = dataState.exception.message
                }
            }
        })
    }

    private fun displayProgressBar(isShowing: Boolean) {
        binding.pB.visibility = if (isShowing) View.VISIBLE else View.GONE
    }
}