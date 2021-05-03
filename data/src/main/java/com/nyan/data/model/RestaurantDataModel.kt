package com.nyan.data.model

data class RestaurantDataModel(
    var id: Int,
    var title: String = "",
    var pictures: ArrayList<String>? = null,
    var rating: Double = 0.0,
    var address: String = "",
    var lat: Double = 1.0,
    var lng: Double = 1.0,
    var desciption: String = "",
    var comments: ArrayList<CommentDataModel>? = null
)