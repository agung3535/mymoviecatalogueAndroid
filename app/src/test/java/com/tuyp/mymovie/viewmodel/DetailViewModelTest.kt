package com.tuyp.mymovie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.tuyp.mymovie.data.local.entity.GenreMovieEntity
import com.tuyp.mymovie.data.local.entity.GenreTvEntity
import com.tuyp.mymovie.data.local.entity.MovieWithGenreEntity
import com.tuyp.mymovie.data.local.entity.TvWithGenreEntity
import com.tuyp.mymovie.data.remote.model.resource.Genre
import com.tuyp.mymovie.data.remote.model.resource.MovieDetail
import com.tuyp.mymovie.data.remote.model.resource.TvShowDetail
import com.tuyp.mymovie.repository.DetailRepository
import com.tuyp.mymovie.utils.DataDummy
import com.tuyp.mymovie.vo.ResourceVO
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.ArrayList

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateMovieLocalDummy()[0]
    private val dummyShow = DataDummy.generateShowLocal()[0]
//    private val dummyMovie = DataDummy.generateMovieAPIDummy()[0]
//    private val dummyShow = DataDummy.generateShowAPI()[0]
    //    private val dummyGenreMovie = DataDummy.generateGenre()[0]
//    private val dummyGenreShow = DataDummy.generateGenreShow()[0]
    private val movieId = dummyMovie.movieId
    private val showId = dummyShow.showId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var detailRepository: DetailRepository

    @Mock
    lateinit var movieObserver: Observer<ResourceVO<MovieWithGenreEntity>>
//    lateinit var movieObserver: Observer<MovieDetail>

    @Mock
    lateinit var showObserver: Observer<ResourceVO<TvWithGenreEntity>>
//    lateinit var showObserver: Observer<TvShowDetail>

    @Before
    fun setup() {
        detailViewModel = DetailViewModel(detailRepository)
    }

    @Test
    fun getDetailMovie() {
        val movies = MutableLiveData<ResourceVO<MovieWithGenreEntity>>()
        var genre = ArrayList<GenreMovieEntity>()
        var dataGenre = DataDummy.generateGenre()
        genre = DataDummy.generateGenre() as ArrayList<GenreMovieEntity>
        if (genre.size != 0) genre.clear()
        for(dataGenres in dataGenre) {
            if (dataGenres.id.toString() == movieId) {
                genre.add(GenreMovieEntity(dataGenres.id.toString(),movieId.toString(), dataGenres.name))
            }
        }
        val detailMovie = ResourceVO.success(DataDummy.generateDetailMovieLocal(dummyMovie,genre))
        movies.value = detailMovie
        detailViewModel.setMovieId(movieId.toString())
        `when`(detailRepository.getDetailMovie(movieId.toString())).thenReturn(movies)
        val dataMovie = detailViewModel.getDetailMovie()
        assertNotNull(dataMovie)
        assertEquals(dummyMovie.movieId, dataMovie.value?.data?.movie?.movieId)
        assertEquals(dummyMovie.title, dataMovie.value?.data?.movie?.title)
        assertEquals(dummyMovie.backdropPath, dataMovie.value?.data?.movie?.backdropPath)
        assertEquals(dummyMovie.originalLanguage, dataMovie.value?.data?.movie?.originalLanguage)
        assertEquals(dummyMovie.overview, dataMovie.value?.data?.movie?.overview)
        assertEquals(dummyMovie.popularity.toString(), dataMovie.value?.data?.movie?.popularity.toString())
        detailViewModel.getDetailMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(detailMovie)
    }

    @Test
    fun getDetailShow() {
        val tvShow = MutableLiveData<ResourceVO<TvWithGenreEntity>>()
        val genre = ArrayList<GenreTvEntity>()
        val dataGenre = DataDummy.generateGenreShow()

        for (dataGenres in dataGenre) {
            if (dataGenres.id.toString() == showId) {
                genre.add(GenreTvEntity(dataGenres.id.toString(),showId,dataGenres.name))
            }
        }

        val detailTv = ResourceVO.success(DataDummy.generateDetailShowLocal(dummyShow, genre))
        tvShow.value = detailTv

        detailViewModel.setShowId(showId.toString())
        `when`(detailRepository.getDetailShow(showId.toString())).thenReturn(tvShow)
        val dataTvShow = detailViewModel.getDetailShow()
        assertNotNull(dataTvShow)
        assertEquals(dummyShow.showId, dataTvShow.value?.data?.tvShow?.showId)
        assertEquals(dummyShow.title, dataTvShow.value?.data?.tvShow?.title)
        assertEquals(dummyShow.backdropPath, dataTvShow.value?.data?.tvShow?.backdropPath)
        assertEquals(dummyShow.originalLanguage, dataTvShow.value?.data?.tvShow?.originalLanguage)
        assertEquals(dummyShow.overview, dataTvShow.value?.data?.tvShow?.overview)
        assertEquals(dummyShow.popularity.toString(), dataTvShow.value?.data?.tvShow?.popularity.toString())
        detailViewModel.getDetailShow().observeForever(showObserver)
        verify(showObserver).onChanged(detailTv)

    }



}