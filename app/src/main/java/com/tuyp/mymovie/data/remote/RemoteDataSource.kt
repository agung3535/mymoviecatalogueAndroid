package com.tuyp.mymovie.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tuyp.mymovie.data.remote.model.resource.MovieDetail
import com.tuyp.mymovie.data.remote.model.resource.MovieResource
import com.tuyp.mymovie.data.remote.model.resource.TvShowDetail
import com.tuyp.mymovie.data.remote.model.resource.TvShowResource
import com.tuyp.mymovie.data.remote.model.response.MovieResponse
import com.tuyp.mymovie.utils.ConstVal
import com.tuyp.mymovie.utils.EspressoIdlingResource
import com.tuyp.mymovie.utils.apiconfig.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val  api: APIService
) {



//    fun loadMovie(callback: LoadMovieCallback) {
//        EspressoIdlingResource.increment()
//        val call = api.getPlayingMovies(ConstVal.API_KEY)
//        call.enqueue(object : Callback<MovieResponse<MovieResource>> {
//            override fun onResponse(
//                call: Call<MovieResponse<MovieResource>>,
//                response: Response<MovieResponse<MovieResource>>
//            ) {
//                if (response.isSuccessful){
//                    EspressoIdlingResource.decrement()
//                    response.body()?.let { callback.onAllMovieReceived(it.results) }
//                }else{
//                    EspressoIdlingResource.decrement()
//                    callback.errorHandling("error")
//                }
//            }
//
//            override fun onFailure(call: Call<MovieResponse<MovieResource>>, t: Throwable) {
//                EspressoIdlingResource.decrement()
//                callback.errorHandling(t.message.toString())
//            }
//
//        })
//    }
    fun loadMovie(): LiveData<ApiResponseRemote<MovieResponse<MovieResource>>> {
        EspressoIdlingResource.increment()
//        val call = api.getPlayingMovies(ConstVal.API_KEY)
        val resultMovie = MutableLiveData<ApiResponseRemote<MovieResponse<MovieResource>>>()
        CoroutineScope(IO).launch {
            resultMovie.postValue(ApiResponseRemote.success(api.getPlayingMovies(ConstVal.API_KEY)))
            EspressoIdlingResource.decrement()
        }
        return resultMovie
    }

//    fun loadTv(callback: LoadTvShowCallback) {
//        EspressoIdlingResource.increment()
//        val call = api.getTv(ConstVal.API_KEY)
//        call.enqueue(object : Callback<MovieResponse<TvShowResource>>{
//            override fun onResponse(
//                call: Call<MovieResponse<TvShowResource>>,
//                response: Response<MovieResponse<TvShowResource>>
//            ) {
//                if (response.isSuccessful){
//                    EspressoIdlingResource.decrement()
//                    response.body()?.let { callback.onAllTvShowReceiverd(it.results) }
//                }else{
//                    EspressoIdlingResource.decrement()
//                    callback.errorHanlding("error")
//                }
//            }
//
//            override fun onFailure(call: Call<MovieResponse<TvShowResource>>, t: Throwable) {
//                EspressoIdlingResource.decrement()
//                callback.errorHanlding(t.message.toString())
//            }
//
//        })
//    }

    fun loadTv(): LiveData<ApiResponseRemote<MovieResponse<TvShowResource>>> {
        EspressoIdlingResource.increment()
        val resultShow = MutableLiveData<ApiResponseRemote<MovieResponse<TvShowResource>>>()
        CoroutineScope(IO).launch {
            resultShow.postValue( ApiResponseRemote.success(api.getTv(ConstVal.API_KEY)))
            EspressoIdlingResource.decrement()
        }
        return resultShow

    }

    fun loadDetailMovie(movieId : String): LiveData<ApiResponseRemote<MovieDetail>> {
        EspressoIdlingResource.increment()
        val resultDetailMovie = MutableLiveData<ApiResponseRemote<MovieDetail>>()
        CoroutineScope(IO).launch {
            resultDetailMovie.postValue(ApiResponseRemote.success(api.getDetailMovie(movieId,ConstVal.API_KEY)))
            EspressoIdlingResource.decrement()
        }
        Log.d("dataaaa","detail moive $resultDetailMovie")
        return resultDetailMovie
    }

//    fun loadDetailMovie(movieId : String,callback: LoadMovieDetailCallback){
//        EspressoIdlingResource.increment()
//        val call = api.getDetailMovie(movieId,ConstVal.API_KEY)
//        call.enqueue(object  : Callback<MovieDetail>{
//            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
//                if (response.isSuccessful){
//                    EspressoIdlingResource.decrement()
//                    response.body()?.let {
//                        callback.loadDetailMovie(it)
//                    }
//                }else{
//                    EspressoIdlingResource.decrement()
//                    Log.d("dataaaa","error")
//                }
//            }
//
//            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
//                EspressoIdlingResource.decrement()
//                Log.d("dataaaa","error = ${t.message.toString()}")
//            }
//
//        })
//    }

    fun loadDetailShow(showId : String): LiveData<ApiResponseRemote<TvShowDetail>>{
        EspressoIdlingResource.increment()
//        val call = api.getDetailShow(showId, ConstVal.API_KEY)
        val resultTvDetail = MutableLiveData<ApiResponseRemote<TvShowDetail>>()
        CoroutineScope(IO).launch {
            resultTvDetail.postValue(ApiResponseRemote.success(api.getDetailShow(showId,ConstVal.API_KEY)))
            EspressoIdlingResource.decrement()
        }
        return resultTvDetail
    }

//    fun loadDetailShow(showId : String, callback: LoadShowDetailCallback){
//        EspressoIdlingResource.increment()
//        val call = api.getDetailShow(showId, ConstVal.API_KEY)
//        call.enqueue(object  : Callback<TvShowDetail>{
//            override fun onResponse(call: Call<TvShowDetail>, response: Response<TvShowDetail>) {
//                if (response.isSuccessful){
//                    EspressoIdlingResource.decrement()
//                    response.body()?.let {
//                        callback.loadDetailShow(it)
//                    }
//                }else{
//                    EspressoIdlingResource.decrement()
//                    Log.d("dataaaa","error")
//                }
//            }
//
//            override fun onFailure(call: Call<TvShowDetail>, t: Throwable) {
//                EspressoIdlingResource.decrement()
//                Log.d("dataaaa","error = ${t.message.toString()}")
//
//            }
//
//        })
//    }



    interface LoadMovieCallback{
        fun onAllMovieReceived(movieResponses : List<MovieResource>)
        fun errorHandling(message : String)
    }

    interface LoadTvShowCallback{
        fun onAllTvShowReceiverd(showResponse : List<TvShowResource>)
        fun errorHanlding(message: String)
    }

    interface LoadMovieDetailCallback{
        fun loadDetailMovie(detailMovie : MovieDetail)
        fun errorHanlding(message: String)
    }

    interface LoadShowDetailCallback{
        fun loadDetailShow(detailShow : TvShowDetail)
        fun errorHanlding(message: String)
    }
}