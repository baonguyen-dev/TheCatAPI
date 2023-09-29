package com.example.the_cat_api.ui.breeds

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.the_cat_api.databinding.FragmentBreedsBinding
import com.example.the_cat_api.ui.breeds.controller.BreedsViewModel
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = BreedsAdapter()
        binding.apply {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}