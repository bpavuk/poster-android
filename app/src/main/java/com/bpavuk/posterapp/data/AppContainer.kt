package com.bpavuk.posterapp.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.bpavuk.posterapp.network.PosterApiInterface
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val defaultPosterRepository: PosterRepository
    val defaultCredentialsDatastore: CredentialsDatastore
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
}