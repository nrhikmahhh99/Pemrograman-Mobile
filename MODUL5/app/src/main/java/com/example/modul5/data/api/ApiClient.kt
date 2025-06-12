package com.example.modul5.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object ApiClient {
    private const val BASE_URL = "https://modul5.free.beeceptor.com"

    private val json = Json { ignoreUnknownKeys = true }

    val retrofit: TasApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(TasApiService::class.java)
}
