package com.tuyp.mymovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.tuyp.mymovie.data.local.entity.MyFavoriteMovie
import com.tuyp.mymovie.data.local.entity.MyFavoriteTvShow
import com.tuyp.mymovie.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
    ): ViewModel() {

    fun getFavoriteMovie(): LiveData<PagedList<MyFavoriteMovie>> = profileRepository.getFavoriteMovie()

    fun getFavoriteShow(): LiveData<PagedList<MyFavoriteTvShow>> = profileRepository.getFavoriteTvShow()

}