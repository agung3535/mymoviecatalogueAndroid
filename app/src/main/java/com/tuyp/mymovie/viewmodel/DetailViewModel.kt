package com.tuyp.mymovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tuyp.mymovie.data.local.entity.*
import com.tuyp.mymovie.data.remote.model.resource.MovieDetail
import com.tuyp.mymovie.data.remote.model.resource.MovieResource
import com.tuyp.mymovie.data.remote.model.resource.TvShowDetail
import com.tuyp.mymovie.data.remote.model.resource.TvShowResource
import com.tuyp.mymovie.repository.DetailRepository
import com.tuyp.mymovie.vo.ResourceVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository
): ViewModel() {
    private lateinit var movieId: String
    private lateinit var showId: String


    fun getDetailMovie(): LiveData<ResourceVO<MovieWithGenreEntity>> = detailRepository.getDetailMovie(movieId)
    fun getDetailShow(): LiveData<ResourceVO<TvWithGenreEntity>> = detailRepository.getDetailShow(showId)
    fun cekFavoriteMovie(): LiveData<MyFavoriteMovie> = detailRepository.cekFavoriteMovie(movieId)
    fun cekFavoriteTv(): LiveData<MyFavoriteTvShow> = detailRepository.cekFavoriteTv(showId)
    fun addFavMovie(movie: MovieEntity) = detailRepository.addFavoriteMovie(movie)
    fun addFavShow(show: TvShowEntity) = detailRepository.addFavoriteTv(show)
    fun deleteFavMovie(movie: MyFavoriteMovie) = detailRepository.deleteFavorite(movie)
    fun deleteFavShow(show: MyFavoriteTvShow) = detailRepository.deleteFavoriteTv(show)

    fun setMovieId(movieId: String) {
        this.movieId = movieId
    }
    fun setShowId(showID : String){
        this.showId = showID
    }
}