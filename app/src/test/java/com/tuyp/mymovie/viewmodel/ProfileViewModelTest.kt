package com.tuyp.mymovie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.tuyp.mymovie.data.local.entity.MovieEntity
import com.tuyp.mymovie.data.local.entity.MyFavoriteMovie
import com.tuyp.mymovie.data.local.entity.MyFavoriteTvShow
import com.tuyp.mymovie.data.local.entity.TvShowEntity
import com.tuyp.mymovie.repository.HomeRepository
import com.tuyp.mymovie.repository.ProfileRepository
import com.tuyp.mymovie.utils.DataDummy
import com.tuyp.mymovie.utils.LiveDataTesUtil
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
class ProfileViewModelTest {
    private lateinit var profileViewModel: ProfileViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var profileRepository: ProfileRepository

    @Mock
    private lateinit var observerMoviePaged: Observer<PagedList<MyFavoriteMovie>>

    @Mock
    private lateinit var observerShowPaged: Observer<PagedList<MyFavoriteTvShow>>

    @Before
    fun setup() {
        profileViewModel = ProfileViewModel(profileRepository)
    }

    @Test
    fun getFavMovie() {
        var dataPagedFavMovie = PagedListUtils.mockPagedList(DataDummy.generateFavoriteMovie())
        var expectedFavMovie = MutableLiveData<PagedList<MyFavoriteMovie>>()
        expectedFavMovie.postValue(dataPagedFavMovie)
        Mockito.`when`(profileRepository.getFavoriteMovie()).thenReturn(expectedFavMovie)
        profileViewModel.getFavoriteMovie().observeForever(observerMoviePaged)
        verify(observerMoviePaged).onChanged(expectedFavMovie.value)
        val expectedValueFavMovie = expectedFavMovie.value
        val actualValueFavMovie = profileRepository.getFavoriteMovie().value
        Assert.assertNotNull(actualValueFavMovie)
        Assert.assertEquals(expectedValueFavMovie,actualValueFavMovie)
        Assert.assertEquals(expectedValueFavMovie,actualValueFavMovie)
        Assert.assertEquals(expectedValueFavMovie?.size,actualValueFavMovie?.size)
    }

    @Test
    fun getFavTv() {
        var dataPagedFavTv = PagedListUtils.mockPagedList(DataDummy.generateFavoriteTv())
        var expectedFavTv = MutableLiveData<PagedList<MyFavoriteTvShow>>()
        expectedFavTv.postValue(dataPagedFavTv)
        Mockito.`when`(profileRepository.getFavoriteTvShow()).thenReturn(expectedFavTv)
        profileViewModel.getFavoriteShow().observeForever(observerShowPaged)
        verify(observerShowPaged).onChanged(expectedFavTv.value)
        val expectedValueFavTv = expectedFavTv.value
        val actualValueFavTv = profileRepository.getFavoriteTvShow().value
        Assert.assertNotNull(actualValueFavTv)
        Assert.assertEquals(expectedValueFavTv,actualValueFavTv)
        Assert.assertEquals(expectedValueFavTv,actualValueFavTv)
        Assert.assertEquals(expectedValueFavTv?.size,actualValueFavTv?.size)
    }
}