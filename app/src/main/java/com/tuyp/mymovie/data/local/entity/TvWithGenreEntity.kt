package com.tuyp.mymovie.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TvWithGenreEntity(

    @Embedded
    var tvShow: TvShowEntity,

    @Relation(parentColumn = "showId",entityColumn = "showId")
    var tvGenre: List<GenreTvEntity>

)

