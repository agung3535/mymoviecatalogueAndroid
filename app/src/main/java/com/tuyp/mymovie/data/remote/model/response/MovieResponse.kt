package com.tuyp.mymovie.data.remote.model.response

import com.google.gson.annotations.SerializedName
import com.tuyp.mymovie.data.remote.model.resource.MovieResource

data class MovieResponse<T>(
    @SerializedName("page") var page : Int,
    @SerializedName("results") var results : List<T>,
    @SerializedName("total_pages") var totalPages : Int,
    @SerializedName("total_results") var totalResults : Int
)
