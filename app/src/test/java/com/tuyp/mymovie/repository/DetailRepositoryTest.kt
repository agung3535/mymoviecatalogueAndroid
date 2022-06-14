package com.tuyp.mymovie.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.tuyp.mymovie.data.local.LocalDataSource
import com.tuyp.mymovie.data.local.entity.*
import com.tuyp.mymovie.data.remote.RemoteDataSource
import com.tuyp.mymovie.data.remote.model.resource.Genre
import com.tuyp.mymovie.utils.AppExecutors
import com.tuyp.mymovie.utils.DataDummy
import com.tuyp.mymovie.utils.LiveDataTesUtil
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class DetailRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val detailRepository = FakeDetailRepository(remote,local,appExecutors)

    private val movieResponse = DataDummy.generateMovieLocalDummy()[0]
    private val favMovie = DataDummy.generateFavoriteMovie()[0]
    private val favMovieId = favMovie.favMovieId
    private val favTv = DataDummy.generateFavoriteTv()[0]
    private val favTvId = favTv.favTvShowId
    private val movieId = movieResponse.movieId
    private val tvShowResponse = DataDummy.generateShowLocal()[0]
    private val tvShowId = tvShowResponse.showId

    @Test
    fun getDetailMovie() {
        val genre = ArrayList<GenreMovieEntity>()
        val dataGenre = DataDummy.generateGenre()
        for (dataGenres in dataGenre) {
            if (dataGenres.id.toString() == movieId) {
                genre.add(GenreMovieEntity(dataGenres.id.toString(),movieId,dataGenres.name))
            }
        }

        val dummyDetailMovie = MutableLiveData<MovieWithGenreEntity>()
        dummyDetailMovie.value = DataDummy.generateDetailMovieLocal(movieResponse, genre)
        Mockito.`when`(local.getDetailMovie(movieId)).thenReturn(dummyDetailMovie)
//        doAnswer { invocation ->
//            (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback)
//                .loadDetailMovie(dummyDetailMovie)
//            null
//        }. `when`(remote).loadDetailMovie(eq(movieId.toString()),any())

        val detailMoviesLiveData = LiveDataTesUtil.getValue(detailRepository.getDetailMovie(movieId.toString()))

        verify(local).getDetailMovie(movieId.toString())

        Assert.assertNotNull(detailMoviesLiveData)
//        assertEquals(movieResponse.title,detailMoviesLiveData.data?.movie?.title)
//        assertEquals(movieResponse.overview,detailMoviesLiveData.data?.movie?.overview)
//        assertEquals(movieResponse.popularity,detailMoviesLiveData.data?.movie?.popularity)
        assertEquals(dummyDetailMovie.value!!.movie.title,detailMoviesLiveData.data?.movie?.title)
        assertEquals(dummyDetailMovie.value!!.movie.overview,detailMoviesLiveData.data?.movie?.overview)
        assertEquals(dummyDetailMovie.value!!.movie.originalLanguage,detailMoviesLiveData.data?.movie?.originalLanguage)
        assertEquals(dummyDetailMovie.value!!.genre[0].name,
            detailMoviesLiveData.data?.genre?.get(0)?.name)
    }

    @Test
    fun getDetailShow(){
        var genre = java.util.ArrayList<GenreTvEntity>()
        var dataGenre = DataDummy.generateGenreShow()
        genre = DataDummy.generateGenreShow() as java.util.ArrayList<GenreTvEntity>
        if (genre.size != null) genre.clear()
        for (dataGenres in dataGenre){
            if (dataGenres.id == tvShowId.toInt()){
                genre.add(GenreTvEntity(dataGenres.id.toString(),tvShowId,dataGenres.name))
            }
        }
        val dummyDetailShow = MutableLiveData<TvWithGenreEntity>()
        dummyDetailShow.value = DataDummy.generateDetailShowLocal(tvShowResponse,genre)
        Mockito.`when`(local.getDetailTvShow(tvShowId)).thenReturn(dummyDetailShow)
//        doAnswer { invocation ->
//            (invocation.arguments[1] as RemoteDataSource.LoadShowDetailCallback)
//                .loadDetailShow(dummyDetailShow)
//            null
//        }.`when`(remote).loadDetailShow(eq(tvShowId.toString()),any())

        val detailShowLiveData = LiveDataTesUtil.getValue(detailRepository.getDetailShow(tvShowId.toString()))

//        verify(remote).loadDetailShow(eq(tvShowId.toString()), any())
        verify(local).getDetailTvShow(tvShowId.toString())
        Assert.assertNotNull(detailShowLiveData)
        assertEquals(dummyDetailShow.value!!.tvShow.showId,detailShowLiveData.data?.tvShow?.showId)
        assertEquals(dummyDetailShow.value!!.tvShow.title,detailShowLiveData.data?.tvShow?.title)
        assertEquals(dummyDetailShow.value!!.tvShow.overview,detailShowLiveData.data?.tvShow?.overview)
        assertEquals(dummyDetailShow.value!!.tvGenre[0].name,
            detailShowLiveData.data?.tvGenre?.get(0)?.name)
    }

    @Test
    fun cekFavoriteMovie() {
        var favoriteMovie = DataDummy.generateFavoriteMovie()
        var getFavorite = MutableLiveData<MyFavoriteMovie>()
        for (fav in favoriteMovie) {
            if (fav.favMovieId == favMovieId) {
                getFavorite.postValue(fav)
            }
        }
        Mockito.`when`(local.getDetailFavMovie(favMovieId)).thenReturn(getFavorite)
        val favoriteMovieLiveData = LiveDataTesUtil.getValue(detailRepository.cekFavoriteMovie(favMovieId))
        verify(local).getDetailFavMovie(favMovieId)
        Assert.assertNotNull(favoriteMovieLiveData)
    }

    @Test
    fun cekFavoriteTv() {
        var favoriteTv = DataDummy.generateFavoriteTv()
        var dataFavorite = MutableLiveData<MyFavoriteTvShow>()
        for (fav in favoriteTv) {
            if (fav.favTvShowId == favTvId) {
                dataFavorite.postValue(fav)
            }
        }

        Mockito.`when`(local.getDetailFavTv(favTvId)).thenReturn(dataFavorite)
        val favoriteTvLiveData = LiveDataTesUtil.getValue(detailRepository.cekFavoriteTv(favTvId))
        verify(local).getDetailFavTv(favTvId)
        Assert.assertNotNull(favoriteTvLiveData)
    }

}