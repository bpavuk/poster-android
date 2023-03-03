package com.bpavuk.posterapp.data

import com.bpavuk.posterapp.model.AuthBody
import com.bpavuk.posterapp.model.AuthResponse
import com.bpavuk.posterapp.model.Post
import com.bpavuk.posterapp.model.User
import com.bpavuk.posterapp.network.PosterApiInterface

interface PosterRepository {
    suspend fun getOnlinePosts(lastPostId: Int): List<Post>
    suspend fun getUser(userId: Int): User

    suspend fun getToken(authBody: AuthBody): AuthResponse

    suspend fun getMe(token: String): User

    suspend fun getPostsByUser(lastPostId: Int, user: User): List<Post>
}

class DefaultPosterRepository(private val posterApiInterface: PosterApiInterface): PosterRepository {
    override suspend fun getOnlinePosts(lastPostId: Int): List<Post> =
        posterApiInterface.getPosts(lastPostId, 3)

    override suspend fun getUser(userId: Int) =
        posterApiInterface.getUserById(userId)

    override suspend fun getToken(authBody: AuthBody): AuthResponse =
        posterApiInterface.getToken(username = authBody.username, password = authBody.password)

    override suspend fun getMe(token: String): User =
        posterApiInterface.getMe("Bearer $token")

    override suspend fun getPostsByUser(lastPostId: Int, user: User): List<Post> =
        posterApiInterface.getPosts(lastPostId = lastPostId, posts = 12, username = user.userName)
}