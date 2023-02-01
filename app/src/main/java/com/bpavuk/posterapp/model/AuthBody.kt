package com.bpavuk.posterapp.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthBody(
    val username: String,
    val password: String,
    val grant_type: String = "",
    val scope: String = "",
    val client_id: String = "",
    val client_secret: String = ""
)
