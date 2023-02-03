package com.bpavuk.posterapp.ui.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpavuk.posterapp.data.PosterRepository
import com.bpavuk.posterapp.model.AuthBody
import com.bpavuk.posterapp.model.User
import kotlinx.coroutines.launch

class LoginScreenViewModel(private val posterRepository: PosterRepository): ViewModel() {
    var uiState by mutableStateOf(LoginScreenUiState())

    private suspend fun getUser(): User? {
        return uiState.token?.let { posterRepository.getMe(token = it) }
    }

    fun login() = viewModelScope.launch {
        uiState = uiState.copy(
            token = posterRepository.getToken(
                authBody = AuthBody(
                    username = uiState.username,
                    password = uiState.password
                )
            ).token
        )
        uiState = uiState.copy(
            loggedInUser = getUser()
        )
    }

    fun inputUsername(username: String) {
        uiState = uiState.copy(
            username = username
        )
    }

    fun inputPassword(password: String) {
        uiState = uiState.copy(
            password = password
        )
    }
}

data class LoginScreenUiState(
    val token: String? = null,
    val username: String = "",
    val password: String = "",
    val loggedInUser: User? = null
)