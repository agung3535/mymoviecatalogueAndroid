package com.tuyp.mymovie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.tuyp.mymovie.data.local.entity.MovieEntity
import com.tuyp.mymovie.data.local.entity.TvShowEntity
import com.tuyp.mymovie.data.remote.model.resource.MovieResource
import com.tuyp.mymovie.data.remote.model.resource.TvShowResource
import com.tuyp.mymovie.repository.HomeRepository
import com.tuyp.mymovie.utils.DataDummy
import com.tuyp.mymovie.utils.PagedListUtils
import com.tuyp.mymovie.vo.ResourceVO
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var homeRepository: HomeRepository

    @Mock
    private lateinit var observerMoviePaged: Observer<ResourceVO<PagedList<MovieEntity>>>
//    private lateinit var observer : Observer<List<MovieResource>>
//    private lateinit var observer : Observer<ResourceVO<List<MovieEntity>>>
    @Mock
    private lateinit var pagedListMove: PagedList<MovieEntity>

    @Mock
    private lateinit var observerShowPaged: Observer<ResourceVO<PagedList<TvShowEntity>>>
//    private lateinit var observerShow : Observer<ResourceVO<List<TvShowEntity>>>

//    private lateinit var observerShow : Observer<List<MovieResource>>


    @Before
    fun setup(){
        homeViewModel = HomeViewModel(homeRepository)
    }

    @Test
    fun getMovie(){
//        val dummyMovie = DataDummy.generateMovieAPIDummy()
        val dummyMovie = ResourceVO.success(DataDummy.generateMovieLocalDummy())
        var dummyMoviePaged = PagedListUtils.mockPagedList(DataDummy.generateMovieLocalDummy())
        var dummyMovies = ResourceVO.success(pagedListMove)

//        val movies = MutableLiveData<List<MovieResource>>()
//        val movies = MutableLiveData<ResourceVO<List<MovieEntity>>>()
        val expectedMovies = MutableLiveData<ResourceVO<PagedList<MovieEntity>>>()
//        movies.value = dummyMovie
        expectedMovies.value = ResourceVO.success(dummyMoviePaged)
        Mockito.`when`(homeRepository.getMovieList()).thenReturn(expectedMovies)
        homeViewModel.listMovieData().observeForever(observerMoviePaged)
        verify(observerMoviePaged).onChanged(expectedMovies.value)
        val expectedValue = expectedMovies.value
        val actualValue = homeViewModel.listMovieData().value
        Assert.assertNotNull(actualValue)
        Assert.assertEquals(expectedValue,actualValue)
        Assert.assertEquals(expectedValue?.data,actualValue?.data)
        Assert.assertEquals(expectedValue?.data?.size,actualValue?.data?.size)
//        val dataMovie = homeViewModel.listMovieData().value
//        verify(homeRepository).getMovieList()
//        Assert.assertNotNull(dataMovie)
//        Assert.assertEquals(10, dataMovie?.data?.size)
//
//        homeViewModel.listMovieData().observeForever(observer)
//        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getTvShow() {
//        val dummyTv = ResourceVO.success(DataDummy.generateShowLocal())
        val dummyTvPageed = PagedListUtils.mockPagedList(DataDummy.generateShowLocal())
        val expectedTv = MutableLiveData<ResourceVO<PagedList<TvShowEntity>>>()
        expectedTv.value = ResourceVO.success(dummyTvPageed)
        Mockito.`when`(homeRepository.getTvShow()).thenReturn(expectedTv)
        homeViewModel.listTvShow().observeForever(observerShowPaged)
        verify(observerShowPaged).onChanged(expectedTv.value)
        val expectedValueTv = expectedTv.value
        val actualValueTv = homeViewModel.listTvShow().value
        Assert.assertNotNull(actualValueTv)
        Assert.assertEquals(expectedValueTv,actualValueTv)
        Assert.assertEquals(expectedValueTv?.data,actualValueTv?.data)
        Assert.assertEquals(expectedValueTv?.data?.size,actualValueTv?.data?.size)

        //before paging
//        val tv = MutableLiveData<ResourceVO<List<TvShowEntity>>>()
//        tv.value = dummyTv
//        Mockito.`when`(homeRepository.getTvShow()).thenReturn(tv)
//        val dataTv = homeViewModel.listTvShow().value
//        verify(homeRepository).getTvShow()
//        Assert.assertNotNull(dataTv)
//        Assert.assertEquals(10,dataTv?.data?.size)
//
//        homeViewModel.listTvShow().observeForever(observerShow)
//        verify(observerShow).onChanged(dummyTv)
    }
}