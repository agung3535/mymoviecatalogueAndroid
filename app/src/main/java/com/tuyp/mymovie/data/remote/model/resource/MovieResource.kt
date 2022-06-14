package com.tuyp.mymovie.data.remote.model.resource

import com.google.gson.annotations.SerializedName

data class MovieResource(
    @SerializedName("id") var id : Int,
    @SerializedName("title") var title : String,
    @SerializedName("backdrop_path") var backdropPath : String,
    @SerializedName("overview") var overview : String,
    @SerializedName("popularity") var popularity : Double,
    @SerializedName("original_language") var originalLanguage : String,
)
