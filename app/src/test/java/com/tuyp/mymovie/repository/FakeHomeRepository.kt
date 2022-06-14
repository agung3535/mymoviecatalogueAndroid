package com.tuyp.mymovie.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.tuyp.mymovie.blueprints.HomeDataSource
import com.tuyp.mymovie.data.NetworkBoundResource
import com.tuyp.mymovie.data.local.LocalDataSource
import com.tuyp.mymovie.data.local.entity.MovieEntity
import com.tuyp.mymovie.data.local.entity.TvShowEntity
import com.tuyp.mymovie.data.remote.ApiResponseRemote
import com.tuyp.mymovie.data.remote.RemoteDataSource
import com.tuyp.mymovie.data.remote.model.resource.MovieResource
import com.tuyp.mymovie.data.remote.model.resource.TvShowResource
import com.tuyp.mymovie.data.remote.model.response.MovieResponse
import com.tuyp.mymovie.utils.AppExecutors
import com.tuyp.mymovie.vo.ResourceVO
import javax.inject.Inject

class FakeHomeRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): HomeDataSource {
    override fun getMovieList(): LiveData<ResourceVO<PagedList<MovieEntity>>> {
        return object: NetworkBoundResource<PagedList<MovieEntity>, MovieResponse<MovieResource>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
//                return localDataSource.getAllMove()
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMove(),config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponseRemote<MovieResponse<MovieResource>>> {
                return remoteDataSource.loadMovie()
            }

            override fun saveCallResult(data: MovieResponse<MovieResource>) {
                val movieList = ArrayList<MovieEntity>()
                Log.d("dataaaaa","data save call ${data.results}")
                for (movie in data.results) {
                    val movies = MovieEntity(movie.id.toString(),
                        movie.title,
                        movie.backdropPath,
                        movie.overview,
                        movie.popularity.toString(),
                        movie.originalLanguage)
                    movieList.add(movies)
                }
                localDataSource.addMovie(movieList)
            }


        }.asLiveData()
    }

    override fun getTvShow(): LiveData<ResourceVO<PagedList<TvShowEntity>>> {
        return object: NetworkBoundResource<PagedList<TvShowEntity>, MovieResponse<TvShowResource>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
//                return localDataSource.getAllTvShow()
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShow(),config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponseRemote<MovieResponse<TvShowResource>>> {
                return remoteDataSource.loadTv()
            }

            override fun saveCallResult(data: MovieResponse<TvShowResource>) {
                val tvList = ArrayList<TvShowEntity>()
                Log.e("dataaa","error ${data.results}")
                for (tv in data.results) {
                    val tvShow = TvShowEntity(
                        tv.id.toString(),
                        tv.name,
                        if (tv.backdropPath != null) tv.backdropPath else "",
                        tv.overview,
                        tv.popularity.toString(),
                        tv.originalLanguage
                    )
                    tvList.add(tvShow)
                }
                localDataSource.addTvShow(tvList)
            }

        }.asLiveData()
    }



}