package com.bpavuk.posterapp.ui.test.fake

import com.bpavuk.posterapp.model.Post
import com.bpavuk.posterapp.model.User
import com.bpavuk.posterapp.network.PosterApiInterface

object FakePosterApiInterface: PosterApiInterface {
    override suspend fun getPosts(lastPostId: Int, posts: Int): List<Post> = Datasource.fakePosts
    override suspend fun getUserById(userId: Int): User {
        TODO("Not yet implemented")
    }
}