package com.nyan.foodie.binding.model.restaurant

import com.nyan.domain.entity.restaurant.CommentEntity
import com.nyan.domain.entity.restaurant.RestaurantEntity
import com.nyan.foodie.binding.model.restaurant.Restaurant as RestaurantBinding
import com.nyan.foodie.binding.model.restaurant.Comment as CommentBinding

object RestaurantConverter {

    fun fromEntityToBinding(restaurantList: List<RestaurantEntity>): List<RestaurantBinding> {
        return restaurantList.map {
            RestaurantBinding(
                id = it.id,
                title = it.title,
                pictures = it.pictures ?: arrayListOf(),
                rating = it.rating,
                address = it.address,
                lat = it.lat,
                lng = it.lng,
                desciption = it.desciption,
                comments = it.comments!!.map {
                    mapCommentToPresentation(it)
                },
                coverPicture = it.coverPicture
            )
        }
    }

    private fun mapCommentToPresentation(comment: CommentEntity) : CommentBinding {
        return CommentBinding(
            name = comment.name,
            comment = comment.comment,
            profile_picture_url = comment.profile_picture_url
        )
    }

    fun fromBindingtoEntity(restaurantList: List<RestaurantBinding>): List<RestaurantEntity> {
        return restaurantList.map {
            RestaurantEntity(
                id = it.id,
                title = it.title,
                pictures = it.pictures ?: arrayListOf(),
                rating = it.rating,
                address = it.address,
                lat = it.lat,
                lng = it.lng,
                desciption = it.desciption,
                comments = it.comments!!.map {
                    mapCommentToEntity(it)
                },
                coverPicture = it.coverPicture
            )
        }
    }

    private fun mapCommentToEntity(comment: CommentBinding) : CommentEntity {
        return CommentEntity(
            name = comment.name,
            comment = comment.comment,
            profile_picture_url = comment.profile_picture_url
        )
    }
}