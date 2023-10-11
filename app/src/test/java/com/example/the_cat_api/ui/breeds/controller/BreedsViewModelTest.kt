package com.example.the_cat_api.ui.breeds.controller

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.the_cat_api.data.model.CatBreed
import com.example.the_cat_api.data.model.CatBreedImage
import com.example.the_cat_api.data.model.CatImage
import com.example.the_cat_api.data.repository.cat_repository.CatRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class BreedsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: BreedsViewModel
    private lateinit var repository: CatRepository
    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        repository = mockk()
        Dispatchers.setMain(dispatcher)
        viewModel = BreedsViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test fetching cat images`() {
        val expectedImages = listOf(CatBreed(id = "1", name = "image_url", referenceImageId = "1"))

        // Mock the repository's getCatImages function
        coEvery {
            repository.getCatBreeds(any(), any())
        } returns expectedImages

        dispatcher.runBlockingTest {
            // Call the function that triggers the API call
            viewModel.getCatImage()

            // Assert that the LiveData contains the expected data
            viewModel.catImages.collect {
                assert(it == expectedImages)
            }
        }
    }
}