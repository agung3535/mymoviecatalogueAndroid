package com.tuyp.mymovie.data.remote.model.resource

import com.google.gson.annotations.SerializedName

data class TvShowResource (
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String,
    @SerializedName("backdrop_path") var backdropPath : String,
    @SerializedName("overview") var overview : String,
    @SerializedName("popularity") var popularity : Double,
    @SerializedName("original_language") var originalLanguage : String,
        )