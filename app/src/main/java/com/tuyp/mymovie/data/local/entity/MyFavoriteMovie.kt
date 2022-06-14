package com.tuyp.mymovie.data.local.entity

import android.icu.text.CaseMap
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteMovies")
data class MyFavoriteMovie(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "favMovieId")
    var favMovieId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "backdropPath")
    var backdropPath: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "popularity")
    var popularity: String,

    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String

)
