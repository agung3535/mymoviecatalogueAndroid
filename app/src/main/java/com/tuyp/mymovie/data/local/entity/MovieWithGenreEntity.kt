package com.tuyp.mymovie.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation


data class MovieWithGenreEntity (
    @Embedded
    var movie: MovieEntity,

    @Relation(parentColumn = "movieId", entityColumn = "movieId")
    var genre: List<GenreMovieEntity>
        )