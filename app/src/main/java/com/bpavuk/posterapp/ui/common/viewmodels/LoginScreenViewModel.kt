package com.bpavuk.posterapp.ui.common.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpavuk.posterapp.data.CredentialsDatastore
import com.bpavuk.posterapp.data.PosterRepository
import com.bpavuk.posterapp.model.AuthBody
import com.bpavuk.posterapp.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginScreenViewModel(
    private val posterRepository: PosterRepository,
    private val credentialsDatastore: CredentialsDatastore
): ViewModel() {
    var uiState by mutableStateOf(
        LoginScreenUiState(
            datastoreUsername = credentialsDatastore.userName,
            datastorePassword = credentialsDatastore.password
        )
    )
    private set

    init {
        fillFromDatastore()
    }

    private fun fillFromDatastore() {
        viewModelScope.launch {
            val credentialsFlow = combine(
                flow = uiState.datastoreUsername,
                flow2 = uiState.datastorePassword
            ) { username, password ->
                buildMap {
                    this["username"] = username
                    this["password"] = password
                }
            }
            credentialsFlow.collect {
                uiState = uiState.copy(
                    username = it["username"] ?: "",
                    password = it["password"] ?: ""
                )
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            with (credentialsDatastore) {
                editUserName(uiState.username)
                editPassword(uiState.password)
            }

            val credentialsFlow = combine(
                flow = uiState.datastoreUsername,
                flow2 = uiState.datastorePassword
            ) { username, password ->
                buildMap {
                    this["username"] = username
                    this["password"] = password
                }
            }
            credentialsFlow.collect {
                try {
                    with(posterRepository) {
                        uiState = uiState.copy(
                            loggedInUser = getMe(
                                getToken(
                                    AuthBody(
                                        username = it["username"] ?: "",
                                        password = it["password"] ?: ""
                                    )
                                ).token
                            ),
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
    val loggedInUser: User? = null,
    val error: HttpError? = null
)

data class HttpError(
    val code: Int,
    val description: String
)