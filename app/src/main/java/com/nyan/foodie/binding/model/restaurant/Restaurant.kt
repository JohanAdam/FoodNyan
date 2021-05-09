package com.nyan.foodie.binding.model.restaurant

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant(
        var id: Int,
        var title: String,
        var pictures: ArrayList<String>,
        var rating: Double,
        var address: String,
        var lat: Double,
        var lng: Double,
        var desciption: String,
        var comments: List<Comment>,
        var coverPicture: String?
) : Parcelable