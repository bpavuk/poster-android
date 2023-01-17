package com.bpavuk.posterapp.data

import com.bpavuk.posterapp.model.Post
import com.bpavuk.posterapp.network.PosterApiInterface

interface PosterRepository {
    suspend fun getOnlinePosts(lastPostId: Int): List<Post>
}

class DefaultPosterRepository(private val posterApiInterface: PosterApiInterface): PosterRepository {
    override suspend fun getOnlinePosts(lastPostId: Int): List<Post> = posterApiInterface.getPosts(lastPostId, 5)
}