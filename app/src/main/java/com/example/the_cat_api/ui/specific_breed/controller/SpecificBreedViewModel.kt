package com.example.the_cat_api.ui.specific_breed.controller

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.the_cat_api.data.model.CatImage
import com.example.the_cat_api.data.repository.cat_repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subscribers.DisposableSubscriber
import javax.inject.Inject


@HiltViewModel
class SpecificBreedViewModel @Inject constructor(
    private val catRepository: CatRepository
) : ViewModel() {

    @SuppressLint("CheckResult")
    fun getCatImages(
        limit: Int = 10,
        breedIds: String = "",
        observer: SingleObserver<List<CatImage>>
    ) {
        catRepository.getCatImages(limit, breedIds)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer)
    }
}