package com.tuyp.mymovie.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.tuyp.mymovie.data.local.entity.MovieEntity
import com.tuyp.mymovie.data.local.entity.TvShowEntity
import com.tuyp.mymovie.data.remote.model.resource.MovieResource
import com.tuyp.mymovie.data.remote.model.resource.TvShowResource
import com.tuyp.mymovie.repository.HomeRepository
import com.tuyp.mymovie.vo.ResourceVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {

//    fun listMovieData(): LiveData<List<MovieResource>> = homeRepository.getMovieList()
    fun listMovieData(): LiveData<ResourceVO<PagedList<MovieEntity>>> = homeRepository.getMovieList()
//    fun listTvShow(): LiveData<List<TvShowResource>> = homeRepository.getTvShow()
fun listTvShow(): LiveData<ResourceVO<PagedList<TvShowEntity>>> = homeRepository.getTvShow()
    fun checkLoading() : LiveData<Boolean> = homeRepository.isLoading
//    fun checkError() : LiveData<String> = homeRepository.errorMessage
    fun checkLoadingTv() : LiveData<Boolean> = homeRepository.isLoadingTv
//    fun checkErrorTv() : LiveData<String> = homeRepository.errorMessageTvShow

}