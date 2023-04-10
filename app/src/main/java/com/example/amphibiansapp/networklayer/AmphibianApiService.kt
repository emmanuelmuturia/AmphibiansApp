package com.example.amphibiansapp.networklayer

import com.example.amphibiansapp.datalayer.AmphibianPhoto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface AmphibianApiService {
        @GET("amphibians")
        suspend fun getAmphibians(): List<AmphibianPhoto>
}