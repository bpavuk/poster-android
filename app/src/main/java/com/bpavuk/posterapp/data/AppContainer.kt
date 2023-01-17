package com.bpavuk.posterapp.data

import com.bpavuk.posterapp.network.PosterApiInterface
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.github.xn32.json5k.Json5
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val defaultPosterRepository: PosterRepository
}

class MockedApiAppContainer: AppContainer {
    private val baseUrl = "https://127.0.0.1:3001/" // Poster API

    private val json = Json5 { prettyPrint = true }

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val retrofitService by lazy { retrofit.create(PosterApiInterface::class.java) }

    override val defaultPosterRepository by lazy {
        DefaultPosterRepository(retrofitService)
    }
}