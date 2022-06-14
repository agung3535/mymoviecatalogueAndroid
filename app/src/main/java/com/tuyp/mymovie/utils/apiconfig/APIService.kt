package com.tuyp.mymovie.utils.apiconfig


import androidx.lifecycle.LiveData
import com.tuyp.mymovie.data.remote.model.resource.MovieDetail
import com.tuyp.mymovie.data.remote.model.resource.MovieResource
import com.tuyp.mymovie.data.remote.model.resource.TvShowDetail
import com.tuyp.mymovie.data.remote.model.resource.TvShowResource
import com.tuyp.mymovie.data.remote.model.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("movie/now_playing")
    suspend fun getPlayingMovies(@Query("api_key") apiKey: String): MovieResponse<MovieResource>

    @GET("tv/airing_today")
    suspend fun getTv(@Query("api_key") api_key : String) : MovieResponse<TvShowResource>

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movie_id : String,
        @Query("api_key") api_key: String,
    ) : MovieDetail

    @GET("tv/{tv_id}")
    suspend fun getDetailShow(
        @Path("tv_id") tv_id : String,
        @Query("api_key") api_key : String,
    ) : TvShowDetail
}