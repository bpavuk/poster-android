package com.bpavuk.posterapp.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthBody(
    val username: String,
    val password: String
)
