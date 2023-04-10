package com.example.amphibiansapp.datalayer

import com.example.amphibiansapp.networklayer.AmphibianApiService

interface AmphibianRepository {
    suspend fun getAmphibianPhotos(): List<AmphibianPhoto>
}


class DefaultAmphibianRepository(private val amphibianApiService: AmphibianApiService) : AmphibianRepository {
    override suspend fun getAmphibianPhotos(): List<AmphibianPhoto> {
        return amphibianApiService.getAmphibians()
    }
}