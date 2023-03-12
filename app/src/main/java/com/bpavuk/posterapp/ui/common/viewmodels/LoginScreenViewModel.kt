package com.bpavuk.posterapp.ui.common.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpavuk.posterapp.domain.screenUseCases.LoginScreenUseCase
import com.bpavuk.posterapp.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginScreenViewModel(
    private val loginScreenUseCase: LoginScreenUseCase
): ViewModel() {
    var uiState by mutableStateOf(
        LoginScreenUiState(
            datastoreUsername = loginScreenUseCase.getUserName(),
            datastorePassword = loginScreenUseCase.getPassword()
        )
    )
    private set

    init {
        fillFromDatastore()
    }

    private fun fillFromDatastore() {
        viewModelScope.launch {
            val credentialFlow = loginScreenUseCase.getCredentialFlow()
            credentialFlow.collect {
                uiState = uiState.copy(
                    username = it["username"] ?: "",
                    password = it["password"] ?: ""
                )
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            with (loginScreenUseCase) {
                editUserName(uiState.username)
                editPassword(uiState.password)
            }
            try {
                with(loginScreenUseCase) {
                    uiState = uiState.copy(
                        loggedInUser = getUser(),
                        error = null
                    )
                }
            } catch (e: HttpException) {
                uiState = uiState.copy(
                    error = HttpError(code = e.code(), description = e.message())
                )
            }
        }
    }

    fun inputUsername(username: String) {
        viewModelScope.launch {
            uiState = uiState.copy(username = username)
        }
    }

    fun inputPassword(password: String) {
        viewModelScope.launch {
            uiState = uiState.copy(password = password)
        }
    }
}

data class LoginScreenUiState(
    val username: String = "",
    val password: String = "",
    val datastoreUsername: Flow<String>,
    val datastorePassword: Flow<String>,
    val loggedInUser: Flow<User>? = null,
    val error: HttpError? = null
)

data class HttpError(
    val code: Int,
    val description: String
)