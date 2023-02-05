package com.bpavuk.posterapp.ui.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpavuk.posterapp.data.PosterRepository
import com.bpavuk.posterapp.data.UserLoginRepository
import com.bpavuk.posterapp.model.AuthBody
import com.bpavuk.posterapp.model.User
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginScreenViewModel(
    private val posterRepository: PosterRepository,
    private val userLoginRepository: UserLoginRepository
): ViewModel() {
    var uiState by mutableStateOf(LoginScreenUiState())
        private set

    init {
        fillUiStateFromDatastore()
        login()
    }

    private suspend fun getUser(): User? {
        return uiState.token?.let { posterRepository.getMe(token = it) }
    }

    fun login() = viewModelScope.launch {
        try {
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
            userLoginRepository.editUserName(userName = uiState.username)
            userLoginRepository.editPassword(password = uiState.password)
        } catch (e: HttpException) {
            uiState = uiState.copy(
                error = HttpError(code = e.code(), e.message())
            )
        }
    }

    private fun fillUiStateFromDatastore() = viewModelScope.launch {
        uiState = uiState.copy(
            username = userLoginRepository.userName.last(),
            password = userLoginRepository.password.last()
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
    val loggedInUser: User? = null,
    val error: HttpError? = null
)

data class HttpError(
    val code: Int,
    val description: String
)