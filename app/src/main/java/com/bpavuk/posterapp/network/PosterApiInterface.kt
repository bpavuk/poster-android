package com.bpavuk.posterapp.network

import com.bpavuk.posterapp.model.AuthBody
import com.bpavuk.posterapp.model.AuthResponse
import com.bpavuk.posterapp.model.Post
import com.bpavuk.posterapp.model.User
import retrofit2.Call
import retrofit2.http.*

interface PosterApiInterface {
    @GET("posts")
    suspend fun getPosts(
        @Query("start") lastPostId: Int,
        @Query("limit") posts: Int
    ): List<Post>
    @GET("user/{id}")
    suspend fun getUserById(@Path("id") userId: Int): User

    @FormUrlEncoded
    @POST("token")
    suspend fun getToken(
        @Field("grant_type") grantType: String = "password",
        @Field("username") username: String,
        @Field("password") password: String
    ): AuthResponse
}