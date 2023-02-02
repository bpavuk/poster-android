package com.bpavuk.posterapp.ui.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpavuk.posterapp.data.PosterRepository
import com.bpavuk.posterapp.model.AuthBody
import kotlinx.coroutines.launch

class LoginScreenViewModel(private val posterRepository: PosterRepository): ViewModel() {
    var uiState by mutableStateOf(LoginScreenUiState())

    fun login() = viewModelScope.launch {
        val password = "fuckery"

        uiState = uiState.copy(
            token = posterRepository.getToken(
                authBody = AuthBody(
                    username = uiState.username,
                    password = password
                )
            ).token
        )
    }

    fun inputUsername(username: String) {
        uiState = uiState.copy(
            username = username
        )
    }
}

data class LoginScreenUiState(
    val token: String? = null,
    val username: String = "",
    val password: String = ""
)