package com.bpavuk.posterapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    @SerialName("access_token")
    val token: String,
    @SerialName("token_type")
    val type: String
)
