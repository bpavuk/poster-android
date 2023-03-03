package com.bpavuk.posterapp.ui.test.fake

import com.bpavuk.posterapp.model.AuthResponse
import com.bpavuk.posterapp.model.Post
import com.bpavuk.posterapp.model.User
import com.bpavuk.posterapp.network.PosterApiInterface

object FakePosterApiInterface: PosterApiInterface {
    override suspend fun getPosts(lastPostId: Int, posts: Int, username: String?): List<Post> = Datasource.fakePosts
    override suspend fun getUserById(userId: Int): User = Datasource.users.first { userId == it.id }
    override suspend fun getToken(
        grantType: String,
        username: String,
        password: String
    ): AuthResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getMe(token: String): User {
        TODO("Not yet implemented")
    }
}