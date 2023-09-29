package com.example.the_cat_api.data.repository.cat_repository

import com.example.the_cat_api.data.model.CatBreed
import com.example.the_cat_api.data.model.CatBreedImage
import com.example.the_cat_api.data.model.CatImage
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Singleton
interface CatRepository {
    suspend fun getCatBreeds(limit: Int, page: Int) : List<CatBreed>
    suspend fun getCatBreedImage(id: String?) : CatBreedImage?
    suspend fun getCatImages(limit: Int, breedIds: String) : List<CatImage>
}