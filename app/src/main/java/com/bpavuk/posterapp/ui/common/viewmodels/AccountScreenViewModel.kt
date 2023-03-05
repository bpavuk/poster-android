package com.bpavuk.posterapp.ui.common.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpavuk.posterapp.data.AuthenticationRepository
import com.bpavuk.posterapp.data.PosterRepository
import com.bpavuk.posterapp.model.Post
import com.bpavuk.posterapp.model.User
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AccountScreenViewModel(
    private val posterRepository: PosterRepository,
    private val authenticationRepository: AuthenticationRepository
): ViewModel() {
    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    init {
        updateInfo()
    }

    fun updateInfo() {
        viewModelScope.launch {
            uiState = UiState.Loading
            try {
                authenticationRepository.getToken().collect {
                    uiState = UiState.Success(
                        user = posterRepository.getMe(it)
                    )
                    uiState = (uiState as UiState.Success).copy(
                        postsList = posterRepository.getPostsByUser(
                            (uiState as UiState.Success).lastPostId,
                            user = (uiState as UiState.Success).user
                        )
                    )
                }
            } catch (e: HttpException) {
                uiState = UiState.Error(code = e.code())
            }
        }
    }
}

sealed interface UiState {
    data class Success(
        val user: User,
        val postsList: List<Post>? = null,
        val lastPostId: Int = 0
    ) : UiState
    data class Error(val code: Int) : UiState
    object Loading: UiState
}