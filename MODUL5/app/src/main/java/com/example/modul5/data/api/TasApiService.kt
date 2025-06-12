package com.example.modul5.data.api

import com.example.modul5.domain.model.Tas
import retrofit2.http.GET

interface TasApiService {
    @GET("data")
    suspend fun getTas(): List<TasDto>
}
