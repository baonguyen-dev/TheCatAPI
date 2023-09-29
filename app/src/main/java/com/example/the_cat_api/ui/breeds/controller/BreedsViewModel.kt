package com.example.the_cat_api.ui.breeds.controller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.the_cat_api.data.model.CatBreed
import com.example.the_cat_api.data.model.CatBreedImage
import com.example.the_cat_api.data.model.CatImage
import com.example.the_cat_api.data.repository.cat_repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val catRepository: CatRepository
) : ViewModel() {

    private val _catImages = MutableSharedFlow<List<CatBreedImage>>()
    val catImages = _catImages.asSharedFlow()

    private var catBreedImages = mutableListOf<CatBreedImage>()

    fun getCatImage(limit: Int = 10, page: Int = 0) {
            viewModelScope.launch {
                val catBreeds = catRepository.getCatBreeds(limit, page)
                if (catBreeds.isNotEmpty())
                    fetchListBreedImage(catBreeds)
        }
    }

    private fun fetchListBreedImage(catBreeds: List<CatBreed>) {
        viewModelScope.launch {
            catBreedImages = mutableListOf()
            for (catBreed in catBreeds) {
                val catBreedImage = catRepository.getCatBreedImage(catBreed.referenceImageId)
                if (catBreedImage != null)
                    catBreedImages.add(CatBreedImage(catBreedImage.id, catBreedImage.url, catBreedImage.breeds))
            }
            _catImages.emit(catBreedImages)
        }
    }
}