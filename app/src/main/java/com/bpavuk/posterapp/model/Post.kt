package com.bpavuk.posterapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val text: String? = null,
    @SerialName("imgURL") val imgURL: String
)
