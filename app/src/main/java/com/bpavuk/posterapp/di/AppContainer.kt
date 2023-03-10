package com.bpavuk.posterapp.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.bpavuk.posterapp.data.CredentialsDatastore
import com.bpavuk.posterapp.data.DefaultPosterRepository
import com.bpavuk.posterapp.data.PosterRepository
import com.bpavuk.posterapp.domain.LoginUseCase
import com.bpavuk.posterapp.domain.PostsUseCase
import com.bpavuk.posterapp.domain.screenUseCases.AccountScreenUseCase
import com.bpavuk.posterapp.domain.screenUseCases.LoginScreenUseCase
import com.bpavuk.posterapp.network.PosterApiInterface
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val defaultLoginScreenUseCase: LoginScreenUseCase
    val defaultPosterRepository: PosterRepository
    val defaultCredentialsDatastore: CredentialsDatastore
    val defaultLoginUseCase: LoginUseCase
    val defaultPostsUseCase: PostsUseCase
    val defaultAccountScreenUseCase: AccountScreenUseCase
}

class MockedApiAppContainer(dataStore: DataStore<Preferences>): AppContainer {
    private val baseUrl = "https://127.0.0.1:3001/" // Poster API

    private val json = Json {
        isLenient = true
        coerceInputValues = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val retrofitService by lazy { retrofit.create(PosterApiInterface::class.java) }

    override val defaultPosterRepository by lazy {
        DefaultPosterRepository(retrofitService)
    }

    override val defaultCredentialsDatastore by lazy {
        CredentialsDatastore(dataStore = dataStore)
    }

    override val defaultLoginUseCase: LoginUseCase by lazy {
        LoginUseCase(
            credentialsDatastore = defaultCredentialsDatastore,
            posterRepository = defaultPosterRepository
        )
    }

    override val defaultPostsUseCase by lazy {
        PostsUseCase(posterRepository = defaultPosterRepository)
    }

    override val defaultAccountScreenUseCase by lazy {
        AccountScreenUseCase(
            loginUseCase = defaultLoginUseCase,
            postsUseCase = defaultPostsUseCase
        )
    }
    override val defaultLoginScreenUseCase by lazy {
        LoginScreenUseCase(loginUseCase = defaultLoginUseCase)
    }
}