package com.bpavuk.posterapp.data

import com.bpavuk.posterapp.model.AuthBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

class AuthenticationRepository(
    private val posterRepository: PosterRepository,
    private val credentialsDatastore: CredentialsDatastore,
) {
    suspend fun getToken(): Flow<String> = flow {
        val credentialsFlow = combine(
            flow = credentialsDatastore.userName,
            flow2 = credentialsDatastore.password
        ) { username, password ->
            buildMap {
                this["username"] = username
                this["password"] = password
            }
        }

        credentialsFlow.collect {
            val token = posterRepository.getToken(
                authBody = AuthBody(
                    username = it["username"] ?: "",
                    password = it["password"] ?: ""
                )
            ).token
            emit(token)
        }
    }
}