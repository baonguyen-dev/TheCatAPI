package com.example.the_cat_api.ui.breeds

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.the_cat_api.R
import com.example.the_cat_api.data.model.CatBreedImage
import com.example.the_cat_api.databinding.FragmentBreedsBinding
import com.example.the_cat_api.ui.breeds.controller.BreedsViewModel
import com.example.the_cat_api.ui.specific_breed.SpecificBreedFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class BreedsFragment : Fragment() {
    private val viewModel: BreedsViewModel by viewModels()
    private lateinit var adapter: BreedsAdapter
    private var _binding: FragmentBreedsBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private var isLoading = false
    private var currentPage = 1

    companion object {
        fun newInstance() = BreedsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBreedsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = BreedsAdapter()
        adapter.clearCatImages()
        currentPage = 1
        binding.apply {
            val layoutManager = rcvCatImages.layoutManager as LinearLayoutManager
            adapter.setOnItemClick {
                val action = BreedsFragmentDirections.actionBreedsFragmentToSpecificBreedFragment(it)
                this@BreedsFragment.findNavController().navigate(action)
            }
            rcvCatImages.adapter = adapter
            rcvCatImages.addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    layoutManager.apply {
                        val totalItemCount = this.itemCount
                        val findLastCompletelyVisibleItemPosition = this.findLastCompletelyVisibleItemPosition()

                        if (!isLoading && findLastCompletelyVisibleItemPosition == totalItemCount - 1) {
                            // Reached the end of the current data set
                            loadNextPage()
                        }
                    }
                }
            })

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.catImages.collect {
                    withContext(Dispatchers.Main) {
                        adapter.setCatImages(it) // Append new data to the adapter
                        isLoading = false // Reset isLoading flag
                    }
                }
            }
        }

        loadData(currentPage)
    }

    private fun loadData(page: Int) {
        isLoading = true
        viewModel.getCatImage(page = page)
    }

    private fun loadNextPage() {
        if (!isLoading) {
            isLoading = true
            currentPage++
            loadData(currentPage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}