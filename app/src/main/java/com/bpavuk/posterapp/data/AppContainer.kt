package com.bpavuk.posterapp.data

import com.bpavuk.posterapp.network.PosterApiInterface
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val defaultPosterRepository: PosterRepository
}

class MockedApiAppContainer: AppContainer {
    private val baseUrl = "https://poster.free.beeceptor.com/"

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val retrofitService by lazy { retrofit.create(PosterApiInterface::class.java) }

    override val defaultPosterRepository by lazy {
        DefaultPosterRepository(retrofitService)
    }
}