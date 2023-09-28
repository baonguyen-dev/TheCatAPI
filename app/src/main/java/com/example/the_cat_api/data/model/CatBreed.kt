package com.example.the_cat_api.data.model

import com.google.gson.annotations.SerializedName

data class CatBreed(
    val id: String,
    val name: String,
    @SerializedName("reference_image_id")
    val referenceImageId: String
)
