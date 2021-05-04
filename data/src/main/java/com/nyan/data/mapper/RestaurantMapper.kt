package com.nyan.data.mapper

import com.nyan.data.model.CommentDataModel
import com.nyan.data.model.RestaurantDataModel
import com.nyan.domain.entity.CommentEntity
import com.nyan.domain.entity.RestaurantEntity

class RestaurantMapper() {

    private fun mapResturantToEntity(restaurantDataModel: RestaurantDataModel) : RestaurantEntity {
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
            }
        )
    }

    private fun mapCommentToEntity(commentDataModel: CommentDataModel) : CommentEntity{
        return CommentEntity(
            name = commentDataModel.userName,
            comment = commentDataModel.userComment,
            profile_picture_url = commentDataModel.profilePictureUrl
        )
    }

    fun mapDataToEntityList(listDataModel: List<RestaurantDataModel>) : List<RestaurantEntity> {
        return listDataModel.map {
            mapResturantToEntity(it)
        }
    }

}