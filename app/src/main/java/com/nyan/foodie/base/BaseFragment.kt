package com.nyan.foodie.base

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nyan.foodie.router.AppRouter
import com.nyan.foodie.router.Router
import com.nyan.foodie.ui.main.MainActivity

abstract class BaseFragment : Fragment() {

    val router: Router
        get() = (activity as MainActivity).router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
}