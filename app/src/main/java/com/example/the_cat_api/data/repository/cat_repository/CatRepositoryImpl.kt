package com.example.the_cat_api.data.repository.cat_repository

import android.util.Log
import com.example.the_cat_api.data.data_source.remote.CatApiService
import com.example.the_cat_api.data.model.CatBreed
import com.example.the_cat_api.data.model.CatBreedImage
import com.example.the_cat_api.data.model.CatImage
import dagger.hilt.android.scopes.ViewModelScoped
import java.util.logging.Logger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatRepositoryImpl @Inject constructor (private val catApiService: CatApiService) : CatRepository {
    override suspend fun getCatBreeds(limit: Int, page: Int): List<CatBreed> {
        try {
            val catBreeds = catApiService.getCatBreeds(limit, page)
            return catBreeds.ifEmpty {
                emptyList()
            }
        }
        catch (ex: Exception) {
            ex.message?.let { Log.e("getCatBreeds", it) }
        }
        return emptyList()
    }

    override suspend fun getCatBreedImage(id: String?): CatBreedImage? {
        try {
            if (!id.isNullOrEmpty())
                return catApiService.getBreedImage(id)
        }
        catch (ex: Exception) {
            ex.message?.let { Log.e("getCatBreedImage", it) }
        }
        return null
    }

    override suspend fun getCatImages(limit: Int, breedIds: String): List<CatImage> {
        try {
            return catApiService.searchImages(limit, breedIds)
        }
        catch (ex: Exception) {
            ex.message?.let { Log.e("getCatImages", it) }
        }
        return emptyList()
    }
}