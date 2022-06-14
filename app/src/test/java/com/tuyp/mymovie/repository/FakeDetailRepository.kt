package com.tuyp.mymovie.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tuyp.mymovie.blueprints.DetailDataSource
import com.tuyp.mymovie.data.NetworkBoundResource
import com.tuyp.mymovie.data.local.LocalDataSource
import com.tuyp.mymovie.data.local.entity.*
import com.tuyp.mymovie.data.remote.ApiResponseRemote
import com.tuyp.mymovie.data.remote.RemoteDataSource
import com.tuyp.mymovie.data.remote.model.resource.MovieDetail
import com.tuyp.mymovie.data.remote.model.resource.TvShowDetail
import com.tuyp.mymovie.utils.AppExecutors
import com.tuyp.mymovie.vo.ResourceVO
import javax.inject.Inject

class FakeDetailRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): DetailDataSource {

    val errorMessage = MutableLiveData<String>()
    val errorMessageTvShow = MutableLiveData<String>()
    val isFavoriteMove = MutableLiveData<Boolean>()
    val isFavoriteTvShow = MutableLiveData<Boolean>()
    override fun getDetailMovie(movieId: String): LiveData<ResourceVO<MovieWithGenreEntity>> {
        return object: NetworkBoundResource<MovieWithGenreEntity,MovieDetail>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieWithGenreEntity> {
                return localDataSource.getDetailMovie(movieId)
            }

            override fun shouldFetch(data: MovieWithGenreEntity?): Boolean {
                return data == null || data.genre.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponseRemote<MovieDetail>> {
                return remoteDataSource.loadDetailMovie(movieId)
            }

            override fun saveCallResult(data: MovieDetail) {
                val genreList = ArrayList<GenreMovieEntity>()
                Log.d("dataaaa","detail ${data.genres}")
                for (genre in data.genres) {
                    val genres = GenreMovieEntity(genre.id.toString(),data.id.toString(),genre.name)
                    genreList.add(genres)
                }
                localDataSource.addGenreMove(genreList)

            }

        }.asLiveData()
    }

    override fun getDetailShow(showId: String): LiveData<ResourceVO<TvWithGenreEntity>> {
        return object: NetworkBoundResource<TvWithGenreEntity, TvShowDetail>(appExecutors) {
            override fun loadFromDB(): LiveData<TvWithGenreEntity> {
                return localDataSource.getDetailTvShow(showId)
            }

            override fun shouldFetch(data: TvWithGenreEntity?): Boolean {
                return data == null || data.tvGenre.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponseRemote<TvShowDetail>> {
                return remoteDataSource.loadDetailShow(showId)
            }

            override fun saveCallResult(data: TvShowDetail) {
                val genreList = ArrayList<GenreTvEntity>()
                for(genre in data.genres) {
                    val genres = GenreTvEntity(genre.id.toString(),data.id.toString(),genre.name)
                    genreList.add(genres)
                }
                localDataSource.addGenreTv(genreList)
            }

        }.asLiveData()
    }

    override fun cekFavoriteMovie(movieId: String): LiveData<MyFavoriteMovie> {
        var favMovie = MutableLiveData<MyFavoriteMovie>()
        val data = localDataSource.getDetailFavMovie(movieId)
        return data
//        favMovie.value = data.value
//        if (data.value == null) {
//            favMovie.postValue(false)
//            return favMovie
//        }else {
//            favMovie.postValue(true)
//            return favMovie
//        }
    }

    override fun cekFavoriteTv(showId: String): LiveData<MyFavoriteTvShow> {
        var favTv = MutableLiveData<Boolean>()
        val data = localDataSource.getDetailFavTv(showId)
        return data
//        if (data.value == null) {
//            favTv.postValue(false)
//            return favTv
//        }else {
//            favTv.postValue(true)
//            return favTv
//        }
    }

    override fun addFavoriteMovie(movie: MovieEntity) {
        if (movie != null) {
            var favMovies = MyFavoriteMovie(movie.movieId.toString(),
                movie.title,
                movie.backdropPath,
                movie.overview,
                movie.popularity.toString(),
                movie.originalLanguage)
            localDataSource.addFavMovie(favMovies)
        }


    }

    override fun addFavoriteTv(show: TvShowEntity) {
        if (show != null) {
            var favShow = MyFavoriteTvShow(show.showId.toString(),
                show.title.toString(),
                show.backdropPath,
                show.overview,
                show.popularity.toString(),
                show.originalLanguage)
            localDataSource.addFavTvShow(favShow)
        }
    }

    override fun deleteFavorite(movie: MyFavoriteMovie) {
        if (movie != null) {
            localDataSource.deleteFavMovie(movie)
        }
    }

    override fun deleteFavoriteTv(show: MyFavoriteTvShow) {
        if (show != null) {

            localDataSource.deleteFavTvShow(show)
        }
    }
}