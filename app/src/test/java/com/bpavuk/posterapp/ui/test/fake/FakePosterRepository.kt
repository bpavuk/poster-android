package com.bpavuk.posterapp.ui.test.fake

import com.bpavuk.posterapp.data.PosterRepository
import com.bpavuk.posterapp.network.PosterApiInterface
import com.bpavuk.posterapp.model.Post

class FakePosterRepository(private val posterApiInterface: PosterApiInterface) : PosterRepository {
    override suspend fun getOnlinePosts(): List<Post> = posterApiInterface.getPosts()
}