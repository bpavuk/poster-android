package com.bpavuk.posterapp.network

import com.bpavuk.posterapp.model.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface PosterApiInterface {
    @GET("posts")
    suspend fun getPosts(
        @Query("from") lastPostId: Int,
        @Query("limit") posts: Int
    ): List<Post>
}