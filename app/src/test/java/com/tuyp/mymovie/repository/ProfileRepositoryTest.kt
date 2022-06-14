package com.tuyp.mymovie.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.tuyp.mymovie.data.local.LocalDataSource
import com.tuyp.mymovie.data.local.entity.MyFavoriteMovie
import com.tuyp.mymovie.data.local.entity.MyFavoriteTvShow
import com.tuyp.mymovie.data.remote.RemoteDataSource
import com.tuyp.mymovie.utils.AppExecutors
import com.tuyp.mymovie.utils.DataDummy
import com.tuyp.mymovie.utils.PagedListUtils
import com.tuyp.mymovie.vo.ResourceVO
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ProfileRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val locale = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private val profileRepository = FakeProfileRepository(locale,appExecutors)

    private val movieResponse = DataDummy.generateFavoriteMovie()
    private val tvShowResponse = DataDummy.generateFavoriteTv()

    @Test
    fun getFavMovie() {
        val dataSourcePaged = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,MyFavoriteMovie>
        `when`(locale.getFavoriteMovie()).thenReturn(dataSourcePaged)
        profileRepository.getFavoriteMovie()
        val favMoviePaged = ResourceVO.success(PagedListUtils.mockPagedList(DataDummy.generateFavoriteMovie()))
        verify(locale).getFavoriteMovie()
        Assert.assertNotNull(favMoviePaged.data)
        Assert.assertEquals(movieResponse.size.toLong(), favMoviePaged.data?.size?.toLong())
    }

    @Test
    fun getFavTv() {
        val dataSourcePaged = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,MyFavoriteTvShow>
        `when`(locale.getFavoriteShow()).thenReturn(dataSourcePaged)
        profileRepository.getFavoriteTvShow()
        val favTvPaged = ResourceVO.success(PagedListUtils.mockPagedList(DataDummy.generateFavoriteTv()))
        verify(locale).getFavoriteShow()
        Assert.assertNotNull(favTvPaged.data)
        Assert.assertEquals(tvShowResponse.size.toLong(),favTvPaged.data?.size?.toLong())
    }
}