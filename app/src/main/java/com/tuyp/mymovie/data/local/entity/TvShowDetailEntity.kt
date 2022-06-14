package com.tuyp.mymovie.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moviesdetailentities")
data class TvShowDetailEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="showId")
    var showId: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "backdropPath")
    var backdropPath: String,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "popularity")
    var popularity: String,
    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String,

        )