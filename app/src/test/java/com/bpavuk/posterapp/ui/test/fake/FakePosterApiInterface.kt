package com.bpavuk.posterapp.ui.test.fake

import com.bpavuk.posterapp.model.Post
import com.bpavuk.posterapp.network.PosterApiInterface

object FakePosterApiInterface: PosterApiInterface {
    override suspend fun getPosts(): List<Post> = Datasource.fakePosts
}