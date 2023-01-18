package com.bpavuk.posterapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    @SerialName("profile_img") val profileImgUrl: String,
    @SerialName("user_name") val userName: String
)
