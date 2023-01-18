package com.bpavuk.posterapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpavuk.posterapp.data.PosterRepository
import com.bpavuk.posterapp.model.Post
import kotlinx.coroutines.launch

class PosterAppViewModel(private val posterRepository: PosterRepository): ViewModel() {
    var uiState by mutableStateOf(PosterUiState())
        private set

    init {
        updatePosts(includeFirst = true)
    }

    fun updatePosts(lastPost: Int = 0, includeFirst: Boolean = false) {
        viewModelScope.launch {
            uiState = uiState.copy(
                postsList = uiState
                    .postsList
                    .plus(
                        with(posterRepository.getOnlinePosts(lastPost)) {
                            if (includeFirst) this
                            else this.subList(1, this.lastIndex)
                        }
                    )
            )
            uiState.postsList.forEach { getAuthorOfPostById(it.authorId, it.id) }
        }
    }

    private suspend fun getAuthorOfPostById(userId: Int, postId: Int) {
        val updatedPost = uiState.postsList.first {
            it.id == postId
        }.apply {
            author = posterRepository.getUser(userId)
        }

        val updatedList = uiState.postsList.toMutableList()
        with(updatedList) {
            this[indexOf(first { it.id == postId })] = updatedPost
        }

        uiState = uiState.copy(
            postsList = updatedList.toList()
        )
    }
}

data class PosterUiState(
    val postsList: List<Post> = emptyList()
)
