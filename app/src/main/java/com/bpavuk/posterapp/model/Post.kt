package com.bpavuk.posterapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Post(
    val id: Int,
    @SerialName("author_id") val authorId: Int,
    val text: String? = null,
    @SerialName("img_url") val imgURL: String,
    @Transient var author: User? = null,
)
