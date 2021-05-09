package com.nyan.foodie.binding.model.restaurant

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
        var name: String,
        var comment: String,
        var profile_picture_url: String,
) : Parcelable
