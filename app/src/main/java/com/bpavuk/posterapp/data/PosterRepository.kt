package com.bpavuk.posterapp.data

import com.bpavuk.posterapp.model.Post
import com.bpavuk.posterapp.network.PosterApiInterface

interface PosterRepository {
    suspend fun getOnlinePosts(): List<Post>
}

class DefaultPosterRepository(private val posterApiInterface: PosterApiInterface): PosterRepository {
    override suspend fun getOnlinePosts(): List<Post> = posterApiInterface.getPosts()
}