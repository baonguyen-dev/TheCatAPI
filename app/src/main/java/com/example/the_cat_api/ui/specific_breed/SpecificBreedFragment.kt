package com.example.the_cat_api.ui.specific_breed

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.the_cat_api.R
import com.example.the_cat_api.data.model.CatImage
import com.example.the_cat_api.databinding.FragmentBreedsBinding
import com.example.the_cat_api.databinding.FragmentSpecificBreedBinding
import com.example.the_cat_api.ui.breeds.BreedsAdapter
import com.example.the_cat_api.ui.breeds.controller.BreedsViewModel
import com.example.the_cat_api.ui.specific_breed.controller.SpecificBreedViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class SpecificBreedFragment : Fragment() {

    private lateinit var breedId: String
    private var disposable: Disposable? = null
    private val viewModel: SpecificBreedViewModel by viewModels()
    private val args: SpecificBreedFragmentArgs by navArgs()
    private lateinit var adapter: SpecificBreedAdapter
    private var _binding: FragmentSpecificBreedBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = SpecificBreedFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSpecificBreedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        breedId = args.breedId
        adapter = SpecificBreedAdapter()
        binding.apply {
            rcvCatImages.adapter = adapter
        }
        viewModel.getCatImages(breedIds = breedId, observer = object: SingleObserver<List<CatImage>> {
            override fun onSubscribe(d: Disposable) {
                // Handle subscription
            }

            override fun onSuccess(t: List<CatImage>) {
                lifecycleScope.launch {
                    withContext(Dispatchers.Main) {
                        adapter.setCatImages(t) // Append new data to the adapter
//                        isLoading = false // Reset isLoading flag
                    }
                }
            }

            override fun onError(e: Throwable) {
                // Handle errors
            }
            })
    }

}