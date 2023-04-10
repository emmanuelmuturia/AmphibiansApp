package com.example.amphibiansapp.networklayer

import com.example.amphibiansapp.datalayer.AmphibianPhoto
import retrofit2.http.GET

interface AmphibianApiService {
        @GET("amphibians")
        suspend fun getAmphibians(): List<AmphibianPhoto>
}