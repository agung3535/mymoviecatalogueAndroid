package com.tuyp.mymovie.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.tuyp.mymovie.blueprints.ProfileDataSource
import com.tuyp.mymovie.data.NetworkBoundResource
import com.tuyp.mymovie.data.local.LocalDataSource
import com.tuyp.mymovie.data.local.entity.MyFavoriteMovie
import com.tuyp.mymovie.data.local.entity.MyFavoriteTvShow
import com.tuyp.mymovie.utils.AppExecutors
import com.tuyp.mymovie.vo.ResourceVO
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    val localDataSource: LocalDataSource,
    val appExecutors: AppExecutors
): ProfileDataSource {
    override fun getFavoriteMovie(): LiveData<PagedList<MyFavoriteMovie>> {
//        val favMovieResult = MutableLiveData<PagedList<MyFavoriteMovie>>()
        val data = localDataSource.getFavoriteMovie()
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(4)
            .setInitialLoadSizeHint(4)
            .build()
//        favMovieResult.value = data.value
        return LivePagedListBuilder(data,config).build()
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<MyFavoriteTvShow>> {
//        val favShowResult = MutableLiveData<PagedList<MyFavoriteTvShow>>()
        val data = localDataSource.getFavoriteShow()
//        favShowResult.value = data.value
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(4)
            .setInitialLoadSizeHint(4)
            .build()
        return LivePagedListBuilder(data,config).build()
    }


}