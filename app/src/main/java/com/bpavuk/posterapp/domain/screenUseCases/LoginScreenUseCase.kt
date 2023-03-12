package com.bpavuk.posterapp.domain.screenUseCases

import com.bpavuk.posterapp.domain.LoginUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class LoginScreenUseCase(
    private val loginUseCase: LoginUseCase
) {
    suspend fun getUser() = loginUseCase.getUser()

    fun getUserName() = loginUseCase.getDatastoreUserName()
    fun getPassword() = loginUseCase.getDatastorePassword()

    suspend fun editUserName(username: String) = loginUseCase.setDatastoreUserName(username)
    suspend fun editPassword(password: String) = loginUseCase.setDatastorePassword(password)
    fun getCredentialFlow(): Flow<Map<String, String>> = combine(
        flow = getUserName(),
        flow2 = getPassword()
    ) { username, password ->
        buildMap {
            this["username"] = username
            this["password"] = password
        }
    }
}