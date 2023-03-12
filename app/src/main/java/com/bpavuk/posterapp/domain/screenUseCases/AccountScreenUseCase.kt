package com.bpavuk.posterapp.domain.screenUseCases

import com.bpavuk.posterapp.domain.LoginUseCase
import com.bpavuk.posterapp.domain.PostsUseCase
import com.bpavuk.posterapp.model.Post
import com.bpavuk.posterapp.model.User

class AccountScreenUseCase(
    private val loginUseCase: LoginUseCase,
    private val postsUseCase: PostsUseCase
) {
    suspend fun getUser() = loginUseCase.getUser()
    suspend fun getPostsByUser(
        lastPostId: Int,
        user: User
    ): List<Post>?
        = postsUseCase.getPostsByUser(lastPostId, user)
}