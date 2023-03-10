package com.bpavuk.posterapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int,
    val text: String,
    @SerialName("img_url") val imgURL: String,
    var author: User
)
