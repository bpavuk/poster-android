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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val posterRepository: PosterRepository,
    private val userLoginRepository: UserLoginRepository
): ViewModel() {
    var uiState by mutableStateOf(
        LoginScreenUiState(
            username = userLoginRepository.userName,
            password = userLoginRepository.password
        )
    )
    private set

    fun login() {
        viewModelScope.launch {
            val credentialsFlow = combine(
                flow = uiState.username,
                flow2 = uiState.password
            ) { username, password ->
                buildMap {
                    this["username"] = username
                    this["password"] = password
                }
            }
            credentialsFlow.collect {
                with(posterRepository) {
                    uiState = uiState.copy(
                        loggedInUser = getMe(
                            getToken(
                                AuthBody(
                                    username = it["username"] ?: "",
                                    password = it["password"] ?: ""
                                )
                            ).token
                        )
                    )
                }
            }
        }
    }

    fun inputUsername(username: String) {
        viewModelScope.launch {
            userLoginRepository.editUserName(username)
        }
    }

    fun inputPassword(password: String) {
        viewModelScope.launch {
            userLoginRepository.editPassword(password)
        }
    }
}

data class LoginScreenUiState(
    val username: Flow<String>,
    val password: Flow<String>,
    val loggedInUser: User? = null,
    val error: HttpError? = null
)

data class HttpError(
    val code: Int,
    val description: String
)