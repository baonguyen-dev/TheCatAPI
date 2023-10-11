package com.example.the_cat_api.data.repository.cat_repository

import com.example.the_cat_api.data.model.CatBreed
import com.example.the_cat_api.data.model.CatBreedImage
import com.example.the_cat_api.data.model.CatImage
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface CatRepository {
    suspend fun getCatBreeds(limit: Int, page: Int) : List<CatBreed>
    suspend fun getCatBreedImage(id: String?) : CatBreedImage?
    fun getCatImages(limit: Int, breedIds: String) : Single<List<CatImage>>
}