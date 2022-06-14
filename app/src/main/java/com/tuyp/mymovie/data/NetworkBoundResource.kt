package com.tuyp.mymovie.data

import android.app.DownloadManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.tuyp.mymovie.data.remote.ApiResponseRemote
import com.tuyp.mymovie.data.remote.StatusResponseRemote
import com.tuyp.mymovie.utils.AppExecutors
import com.tuyp.mymovie.vo.ResourceVO
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow


//inline fun <ResultType, RequestType> networkBoundResource(
//    crossinline query: () -> Flow<ResultType>,
//    crossinline fetch: suspend () -> RequestType,
//    crossinline saveFetchResult: suspend (RequestType) -> Unit,
//    crossinline shouldFetch: (ResultType) -> Boolean = { true },
//    crossinline onFetchSuccess: () -> Unit = { },
//    crossinline onFetchFailed: (Throwable) -> Unit = { }
//) = channelFlow {
//    val data = query().first()
//
//    if (shouldFetch(data)) {
//        val loading = launch {
//            query().collect { send(ResourceVO.loading(it)) }
//        }
//
//        try {
//            saveFetchResult(fetch())
//            onFetchSuccess()
//            loading.cancel()
//            query().collect { send(ResourceVO.success(it)) }
//        } catch (t: Throwable) {
//            onFetchFailed(t)
//            loading.cancel()
//            query().collect { send(ResourceVO.error(t.toString(), it)) }
//        }
//    } else {
//        query().collect { send(ResourceVO.success(it)) }
//    }
//}
abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {
    private val result = MediatorLiveData<ResourceVO<ResultType>>()

    init {
        result.value = ResourceVO.loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = ResourceVO.success(newData)
                }
            }
        }
    }

    protected fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponseRemote<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) {newData ->
            result.value = ResourceVO.loading(newData)
        }

        result.addSource(apiResponse) {response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response.statusResponseRemote) {
                StatusResponseRemote.SUCCESS ->
                    mExecutors.diskIO().execute {
                        saveCallResult(response.body)
                        mExecutors.mainThread().execute {
                            result.addSource(loadFromDB()) {newData ->
                                result.value = ResourceVO.success(newData)
                            }
                        }
                    }
                StatusResponseRemote.EMPTY ->
                    mExecutors.mainThread().execute {
                        result.addSource(loadFromDB()) {newData ->
                            result.value = ResourceVO.success(newData)
                        }
                    }
                StatusResponseRemote.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) {newData ->
                        result.value = ResourceVO.error(response.message, newData)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<ResourceVO<ResultType>> = result
}