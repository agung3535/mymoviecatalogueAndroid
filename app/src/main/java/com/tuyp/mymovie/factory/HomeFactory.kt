package com.tuyp.mymovie.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tuyp.mymovie.repository.HomeRepository
import com.tuyp.mymovie.viewmodel.HomeViewModel
import javax.inject.Inject

class HomeFactory @Inject constructor(
    private val homeRepository: HomeRepository
    ): ViewModelProvider.NewInstanceFactory() {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(homeRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}