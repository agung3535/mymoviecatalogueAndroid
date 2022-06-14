package com.tuyp.mymovie.data.remote.model.resource

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String
)
