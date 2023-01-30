package com.bpavuk.posterapp.data

import com.bpavuk.posterapp.model.Post
import com.bpavuk.posterapp.model.User
import com.bpavuk.posterapp.network.PosterApiInterface

interface PosterRepository {
    suspend fun getOnlinePosts(lastPostId: Int): List<Post>
    suspend fun getUser(userId: Int): User
}

class DefaultPosterRepository(private val posterApiInterface: PosterApiInterface): PosterRepository {
    override suspend fun getOnlinePosts(lastPostId: Int): List<Post> =
        posterApiInterface.getPosts(lastPostId, 3)

    override suspend fun getUser(userId: Int) =
        posterApiInterface.getUserById(userId)
}