package com.nyan.domain.entity.restaurant

data class RestaurantEntity(
    var id: Int,
    var title: String,
    var pictures: ArrayList<String>,
    var rating: Double,
    var address: String,
    var lat: Double,
    var lng: Double,
    var desciption: String,
    var comments: List<CommentEntity>,
    var coverPicture: String?
)
