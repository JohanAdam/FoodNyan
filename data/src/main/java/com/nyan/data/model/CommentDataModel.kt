package com.nyan.data.model

import com.google.gson.annotations.SerializedName

data class CommentDataModel(
    @SerializedName("name")
    var userName: String = "",
    @SerializedName("comment")
    var userComment: String = "",
    @SerializedName("profile_picture_url")
    var profilePictureUrl: String = "",
)