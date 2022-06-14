package com.tuyp.mymovie.blueprints

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.tuyp.mymovie.data.local.entity.MovieEntity
import com.tuyp.mymovie.data.local.entity.TvShowEntity
import com.tuyp.mymovie.data.remote.model.resource.MovieResource
import com.tuyp.mymovie.data.remote.model.resource.TvShowResource
import com.tuyp.mymovie.vo.ResourceVO

interface HomeDataSource {

    fun getMovieList() : LiveData<ResourceVO<PagedList<MovieEntity>>>
    fun getTvShow(): LiveData<ResourceVO<PagedList<TvShowEntity>>>

}