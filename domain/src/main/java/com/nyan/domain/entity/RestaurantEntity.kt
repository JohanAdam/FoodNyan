package com.nyan.domain.entity

data class RestaurantEntity(
    var id: Int,
    var title: String,
    var pictures: ArrayList<String>,
    var rating: Double,
    var address: String,
    var lat: Double,
    var lng: Double,
    var desciption: String,
    var comments: ArrayList<CommentEntity>
)
