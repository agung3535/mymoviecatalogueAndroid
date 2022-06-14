package com.tuyp.mymovie.vo

data class ResourceVO<T>(
    val status: Status,
    val data: T?,
    val message: String?
) {
    companion object {
        fun <T> success(data: T?): ResourceVO<T> = ResourceVO(Status.SUCCESS, data, null)

        fun <T> error(msg: String?, data: T?): ResourceVO<T> = ResourceVO(Status.ERROR, data, msg)

        fun <T> loading(data: T?): ResourceVO<T> = ResourceVO(Status.LOADING, data, null)
    }
}
