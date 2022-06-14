package com.tuyp.mymovie.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tuyp.mymovie.repository.DetailRepository
import com.tuyp.mymovie.viewmodel.DetailViewModel
import com.tuyp.mymovie.viewmodel.HomeViewModel
import javax.inject.Inject

class DetailFactory @Inject constructor(
    private val detailRepository: DetailRepository
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(detailRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}