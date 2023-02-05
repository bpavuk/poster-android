package com.bpavuk.posterapp.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthBody(
    var username: String,
    var password: String,
    val grant_type: String = "",
    val scope: String = "",
    val client_id: String = "",
    val client_secret: String = ""
)
