package com.tuyp.mymovie.data.remote

import retrofit2.http.Body

class ApiResponseRemote<T>(val statusResponseRemote: StatusResponseRemote, val body: T, val message: String?) {
    companion object {
        fun <T> success(body: T): ApiResponseRemote<T> = ApiResponseRemote(StatusResponseRemote.SUCCESS,body,null)
        fun <T> empty(msg: String, body: T): ApiResponseRemote<T> = ApiResponseRemote(StatusResponseRemote.EMPTY,body,msg)
        fun <T> error(msg: String, body: T): ApiResponseRemote<T> = ApiResponseRemote(StatusResponseRemote.ERROR,body,msg)

    }
}