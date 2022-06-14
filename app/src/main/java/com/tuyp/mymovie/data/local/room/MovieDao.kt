package com.tuyp.mymovie.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.tuyp.mymovie.data.local.entity.*
import com.tuyp.mymovie.data.remote.model.resource.MovieResource


@Dao
interface MovieDao {


    @Query("SELECT * FROM movieentities")
    fun getMovie(): DataSource.Factory<Int,MovieEntity>

    @Query("SELECT * FROM movieentities where movieId = :movieId")
    fun getDetailMovie(movieId: String): LiveData<MovieWithGenreEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenreMove(genre: List<GenreMovieEntity>)



    @Query("SELECT * FROM tvshowentities")
    fun getTvShow(): DataSource.Factory<Int,TvShowEntity>

    @Query("SELECT * FROM tvshowentities where showId = :showId")
    fun getDetailTvShow(showId: String): LiveData<TvWithGenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(show: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenreTv(genre: List<GenreTvEntity>)



    @Query("SELECT * FROM favoriteMovies")
    fun getFavMovie(): DataSource.Factory<Int,MyFavoriteMovie>

    @Query("SELECT * FROM favoriteMovies where favMovieId = :favMovieId")
    fun getDetailFavMovie(favMovieId: String): LiveData<MyFavoriteMovie>

    @Query("SELECT * FROM favoriteTvShow")
    fun getFavTv(): DataSource.Factory<Int,MyFavoriteTvShow>

    @Query("SELECT * FROM favoriteTvShow where favTvShowId = :tvShowId")
    fun getDetailFavTv(tvShowId: String): LiveData<MyFavoriteTvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavMovie(movie: MyFavoriteMovie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavShow(tv: MyFavoriteTvShow)

    @Delete
    fun deleteFavMovie(favMovie: MyFavoriteMovie)

    @Delete
    fun deleteFavTvShow(favTv: MyFavoriteTvShow)

}