package com.nyan.data.mapper

import com.nyan.data.model.restaurant.CommentDataModel
import com.nyan.data.model.restaurant.RestaurantDataModel
import com.nyan.domain.entity.restaurant.CommentEntity
import com.nyan.domain.entity.restaurant.RestaurantEntity

class RestaurantMapper() {

    private fun mapRestaurantToEntity(restaurantDataModel: RestaurantDataModel) : RestaurantEntity {
        return RestaurantEntity(
            id = restaurantDataModel.id,
            title = restaurantDataModel.restaurantTitle,
            pictures = restaurantDataModel.restaurantPictures ?: arrayListOf(),
            rating = restaurantDataModel.restaurantRating,
            address = restaurantDataModel.restaurantAddress,
            lat = restaurantDataModel.restaurantLat,
            lng = restaurantDataModel.restaurantLng,
            desciption = restaurantDataModel.restaurantDesciption,
            comments = restaurantDataModel.restaurantComments!!.map {
                mapCommentToEntity(it)
            },
            coverPicture = restaurantDataModel.coverPicture
        )
    }

    private fun mapCommentToEntity(commentDataModel: CommentDataModel) : CommentEntity {
        return CommentEntity(
            name = commentDataModel.userName,
            comment = commentDataModel.userComment,
            profile_picture_url = commentDataModel.profilePictureUrl
        )
    }

    fun mapDataToEntityList(listDataModel: List<RestaurantDataModel>) : List<RestaurantEntity> {
        return listDataModel.map {
            mapRestaurantToEntity(it)
        }
    }

}