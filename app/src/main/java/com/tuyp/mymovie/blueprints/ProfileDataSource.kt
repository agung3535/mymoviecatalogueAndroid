package com.tuyp.mymovie.blueprints

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.tuyp.mymovie.data.local.entity.MyFavoriteMovie
import com.tuyp.mymovie.data.local.entity.MyFavoriteTvShow
import com.tuyp.mymovie.vo.ResourceVO

interface ProfileDataSource {
    fun getFavoriteMovie(): LiveData<PagedList<MyFavoriteMovie>>
    fun getFavoriteTvShow(): LiveData<PagedList<MyFavoriteTvShow>>

}