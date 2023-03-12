package com.bpavuk.posterapp.domain

import com.bpavuk.posterapp.data.PosterRepository
import com.bpavuk.posterapp.model.User

class PostsUseCase(private val posterRepository: PosterRepository) {
    suspend fun getPostsByUser(lastPostId: Int, user: User) =
        posterRepository.getPostsByUser(lastPostId, user)
}