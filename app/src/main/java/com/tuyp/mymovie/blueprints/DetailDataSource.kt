package com.tuyp.mymovie.blueprints

import androidx.lifecycle.LiveData
import com.tuyp.mymovie.data.local.entity.*
import com.tuyp.mymovie.data.remote.model.resource.MovieDetail
import com.tuyp.mymovie.data.remote.model.resource.MovieResource
import com.tuyp.mymovie.data.remote.model.resource.TvShowDetail
import com.tuyp.mymovie.data.remote.model.resource.TvShowResource
import com.tuyp.mymovie.vo.ResourceVO

interface DetailDataSource {
    fun getDetailMovie(movieId : String) : LiveData<ResourceVO<MovieWithGenreEntity>>
    fun getDetailShow(showId : String) : LiveData<ResourceVO<TvWithGenreEntity>>
    fun cekFavoriteMovie(movieId: String): LiveData<MyFavoriteMovie>
    fun cekFavoriteTv(showId: String): LiveData<MyFavoriteTvShow>
    fun addFavoriteMovie(movie: MovieEntity)
    fun addFavoriteTv(show: TvShowEntity)
    fun deleteFavorite(movie: MyFavoriteMovie)
    fun deleteFavoriteTv(show: MyFavoriteTvShow)
}