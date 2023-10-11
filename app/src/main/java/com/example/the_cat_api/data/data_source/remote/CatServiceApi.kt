package com.example.the_cat_api.data.data_source.remote

import com.example.the_cat_api.data.model.CatBreed
import com.example.the_cat_api.data.model.CatBreedImage
import com.example.the_cat_api.data.model.CatImage
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CatApiService {
    @GET("images/search")
    fun searchImages(
        @Query("limit") limit: Int,
        @Query("breed_ids") breedIds: String
    ): Single<List<CatImage>>

    @GET("breeds/")
    suspend fun getCatBreeds(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ) : List<CatBreed>

    @GET("images/{image_id}")
    suspend fun getBreedImage(
        @Path("image_id") imageId: String?
    ) : CatBreedImage
}
