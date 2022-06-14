package com.tuyp.mymovie.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.tuyp.mymovie.data.local.entity.*
import com.tuyp.mymovie.data.local.room.MovieDao
import com.tuyp.mymovie.utils.EspressoIdlingResource
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val movieDao: MovieDao){


    fun getAllMove(): DataSource.Factory<Int,MovieEntity> = movieDao.getMovie()


    fun getAllTvShow(): DataSource.Factory<Int,TvShowEntity> = movieDao.getTvShow()

    fun getDetailMovie(movieId: String): LiveData<MovieWithGenreEntity> = movieDao.getDetailMovie(movieId)

    fun getDetailTvShow(showId: String): LiveData<TvWithGenreEntity> = movieDao.getDetailTvShow(showId)


    fun getFavoriteMovie(): DataSource.Factory<Int, MyFavoriteMovie> = movieDao.getFavMovie()


    fun getFavoriteShow(): DataSource.Factory<Int,MyFavoriteTvShow> = movieDao.getFavTv()

    fun getDetailFavMovie(movieId: String): LiveData<MyFavoriteMovie> = movieDao.getDetailFavMovie(movieId)

    fun getDetailFavTv(showId: String): LiveData<MyFavoriteTvShow> = movieDao.getDetailFavTv(showId)

    fun addMovie(movie: List<MovieEntity>) {

        movieDao.insertMovie(movie)

    }

    fun addGenreMove(genre: List<GenreMovieEntity>) = movieDao.insertGenreMove(genre)

    fun addTvShow(tvShow: List<TvShowEntity>) = movieDao.insertTvShow(tvShow)

    fun addGenreTv(genre: List<GenreTvEntity>) = movieDao.insertGenreTv(genre)

    fun addFavMovie(favMovie: MyFavoriteMovie) {

        movieDao.addFavMovie(favMovie)

    }

    fun addFavTvShow(tvShow: MyFavoriteTvShow) = movieDao.addFavShow(tvShow)

    fun deleteFavMovie(favMovie: MyFavoriteMovie) = movieDao.deleteFavMovie(favMovie)

    fun deleteFavTvShow(favTvShow: MyFavoriteTvShow) = movieDao.deleteFavTvShow(favTvShow)




}