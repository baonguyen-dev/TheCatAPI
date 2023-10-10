package com.example.the_cat_api.ui.specific_breed

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.the_cat_api.R
import com.example.the_cat_api.ui.specific_breed.controller.SpecificBreedViewModel

class SpecificBreedFragment : Fragment() {

    companion object {
        fun newInstance() = SpecificBreedFragment()
    }

    private lateinit var viewModel: SpecificBreedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_specific_breed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SpecificBreedViewModel::class.java]
        // TODO: Use the ViewModel
    }

}