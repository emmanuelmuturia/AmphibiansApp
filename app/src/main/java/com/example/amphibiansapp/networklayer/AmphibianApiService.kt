package com.example.amphibiansapp.networklayer

import com.example.amphibiansapp.datalayer.AmphibianPhoto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

@OptIn(ExperimentalSerializationApi::class)
private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

interface AmphibianApiService {
        @GET("amphibians")
        suspend fun getAmphibians(): List<AmphibianPhoto>
}


object AmphibianApi {
        val retrofitService: AmphibianApiService by lazy {
                retrofit.create(AmphibianApiService::class.java)
        }
}