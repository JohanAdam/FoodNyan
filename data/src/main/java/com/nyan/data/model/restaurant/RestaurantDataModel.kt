package com.nyan.data.model.restaurant

import com.google.gson.annotations.SerializedName

data class RestaurantDataModel(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var restaurantTitle: String = "",
    @SerializedName("pictures")
    var restaurantPictures: ArrayList<String>? = null,
    @SerializedName("rating")
    var restaurantRating: Double = 0.0,
    @SerializedName("address")
    var restaurantAddress: String = "",
    @SerializedName("lat")
    var restaurantLat: Double = 3.1390,
    @SerializedName("lng")
    var restaurantLng: Double = 101.6869,
    @SerializedName("desciption")
    var restaurantDesciption: String = "",
    @SerializedName("comments")
    var restaurantComments: ArrayList<CommentDataModel>? = null
)