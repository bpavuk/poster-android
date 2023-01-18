package com.bpavuk.posterapp.ui.test.fake

import com.bpavuk.posterapp.data.PosterRepository
import com.bpavuk.posterapp.network.PosterApiInterface
import com.bpavuk.posterapp.model.Post
import com.bpavuk.posterapp.model.User

class FakePosterRepository(private val posterApiInterface: PosterApiInterface) : PosterRepository {
    override suspend fun getOnlinePosts(lastPostId: Int): List<Post> = posterApiInterface.getPosts(lastPostId, 5)
    override suspend fun getUser(userId: Int): User {
        TODO("Not yet implemented")
    }
}