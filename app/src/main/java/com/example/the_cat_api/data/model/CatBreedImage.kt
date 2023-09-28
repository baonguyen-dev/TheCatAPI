package com.example.the_cat_api.data.model

data class CatBreedImage(
    val id: String,
    val url: String,
    val breeds: List<CatBreed>
)
