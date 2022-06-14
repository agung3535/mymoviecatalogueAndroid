package com.tuyp.mymovie.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import com.tuyp.mymovie.data.local.LocalDataSource
import com.tuyp.mymovie.data.local.entity.MovieEntity
import com.tuyp.mymovie.data.local.entity.TvShowEntity
import org.mockito.Mockito.mock
import com.tuyp.mymovie.data.remote.RemoteDataSource
import com.tuyp.mymovie.utils.AppExecutors
import com.tuyp.mymovie.utils.DataDummy
import com.tuyp.mymovie.utils.LiveDataTesUtil
import com.tuyp.mymovie.utils.PagedListUtils
import com.tuyp.mymovie.vo.ResourceVO
import junit.framework.TestCase

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`


class HomeRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val locale = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val homeRepository = FakeHomeRepository(remote,locale,appExecutors)

    private val movieResponse = DataDummy.generateMovieLocalDummy()
    private val tvShowResponse = DataDummy.generateShowLocal()

    @Test
    fun getMovies(){
//        doAnswer { invocation ->
//            (invocation.arguments[0] as RemoteDataSource.LoadMovieCallback)
//                .onAllMovieReceived(movieResponse)
//            null
//        }.`when`(remote).loadMovie(any())
//        val dataMovies = LiveDataTesUtil.getValue(homeRepository.getMovieList())
//        verify(remote).loadMovie(any())
//        Assert.assertNotNull(dataMovies)
//        assertEquals(movieResponse.size.toLong(),dataMovies.size.toLong())
//        val dummyMovie = MutableLiveData<List<MovieEntity>>()
        val dataSoruceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,MovieEntity>
//        dummyMovie.value = DataDummy.generateMovieLocalDummy()
        `when`(locale.getAllMove()).thenReturn(dataSoruceFactory)
        homeRepository.getMovieList()
//        val movieEntities = LiveDataTesUtil.getValue(homeRepository.getMovieList())
        val movieEntitiesPaged = ResourceVO.success(PagedListUtils.mockPagedList(DataDummy.generateMovieLocalDummy()))
        verify(locale).getAllMove()
        assertNotNull(movieEntitiesPaged.data)
        assertEquals(movieResponse.size.toLong(),movieEntitiesPaged.data?.size?.toLong())
    }

    @Test
    fun getShows(){
//        doAnswer { invocation ->
//            (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback)
//                .onAllTvShowReceiverd(tvShowResponse)
//            null
//        }.`when`(remote).loadTv(any())
//        val dataShows = LiveDataTesUtil.getValue(homeRepository.getTvShow())
//        verify(remote).loadTv(any())
//        Assert.assertNotNull(dataShows)
//        assertEquals(tvShowResponse.size.toLong(),dataShows.size.toLong())
//        val dummyShow = MutableLiveData<List<TvShowEntity>>()
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,TvShowEntity>
//        dummyShow.value = DataDummy.generateShowLocal()
        `when`(locale.getAllTvShow()).thenReturn(dataSourceFactory)
        homeRepository.getTvShow()
//        val tvEntites = LiveDataTesUtil.getValue(homeRepository.getTvShow())
        val tvEntitiesPaged = ResourceVO.success(PagedListUtils.mockPagedList(DataDummy.generateShowLocal()))
        verify(locale).getAllTvShow()
        assertNotNull(tvEntitiesPaged.data)
        assertEquals(tvShowResponse.size.toLong(),tvEntitiesPaged.data?.size?.toLong())
    }
}