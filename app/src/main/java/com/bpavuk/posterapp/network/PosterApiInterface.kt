package com.bpavuk.posterapp.network

import com.bpavuk.posterapp.model.Post
import retrofit2.http.GET

interface PosterApiInterface {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}