package com.bpavuk.posterapp.domain

import com.bpavuk.posterapp.data.CredentialsDatastore
import com.bpavuk.posterapp.data.PosterRepository
import com.bpavuk.posterapp.model.AuthBody
import com.bpavuk.posterapp.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class LoginUseCase(
    private val posterRepository: PosterRepository,
    private val credentialsDatastore: CredentialsDatastore,
) {
    private suspend fun getToken(): Flow<String> = flow {
        val credentialFlow = combine(
            flow = credentialsDatastore.userName,
            flow2 = credentialsDatastore.password
        ) { username, password ->
            buildMap {
                this["username"] = username
                this["password"] = password
            }
        }

        credentialFlow.collect {
            val token = posterRepository.getToken(
                authBody = AuthBody(
                    username = it["username"] ?: "",
                    password = it["password"] ?: ""
                )
            ).token
            emit(token)
        }
    }

    suspend fun getUser(token: String): Flow<User> = flow {
        emit(posterRepository.getMe(token))
    }

    suspend fun getUser(): Flow<User> {
        val tokenFlow = getToken()
        return tokenFlow.map { posterRepository.getMe(it) }
    }

    fun getDatastoreUserName(): Flow<String> = credentialsDatastore.userName
    fun getDatastorePassword(): Flow<String> = credentialsDatastore.password
    suspend fun setDatastoreUserName(username: String) = credentialsDatastore.editUserName(username)
    suspend fun setDatastorePassword(password: String) = credentialsDatastore.editPassword(password)

}