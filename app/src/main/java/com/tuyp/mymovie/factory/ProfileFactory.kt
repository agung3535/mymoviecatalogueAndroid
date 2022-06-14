package com.tuyp.mymovie.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tuyp.mymovie.repository.HomeRepository
import com.tuyp.mymovie.repository.ProfileRepository
import com.tuyp.mymovie.viewmodel.HomeViewModel
import com.tuyp.mymovie.viewmodel.ProfileViewModel
import javax.inject.Inject

class ProfileFactory @Inject constructor(
    private val profileRepository: ProfileRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                return ProfileViewModel(profileRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}

