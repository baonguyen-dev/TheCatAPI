package com.example.the_cat_api.ui.breeds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.the_cat_api.R
import com.example.the_cat_api.databinding.ActivityBreedsBinding
import com.example.the_cat_api.ui.breeds.controller.BreedsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class BreedsActivity : AppCompatActivity() {
    private val viewModel: BreedsViewModel by viewModels()
    private lateinit var binding: ActivityBreedsBinding
    private lateinit var adapter: BreedsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBreedsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = BreedsAdapter()
        binding.run {
            rcvCatImages.adapter = adapter
            btnClickMe.setOnClickListener {
                viewModel.getCatImage()
            }

            lifecycleScope.launch {
                viewModel.catImages.collect {
                    withContext(Dispatchers.Main) {
                        adapter.setCatImages(it)
                    }
                }
            }
        }
    }
}