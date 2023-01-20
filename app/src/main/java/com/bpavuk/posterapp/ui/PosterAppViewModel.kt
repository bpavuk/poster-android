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
                            if (this.size > 1) {
                                if (includeFirst) this
                                else this.subList(1, this.lastIndex)
                            } else {
                                emptyList()
                            }
                        }
                    )
            )
        }
    }
}

data class PosterUiState(
    val postsList: List<Post> = emptyList()
)
