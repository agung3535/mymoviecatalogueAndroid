package com.tuyp.mymovie.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(tableName = "genretventites",
    primaryKeys = ["genreId","showId"],
    foreignKeys = [ForeignKey(entity = TvShowEntity::class, parentColumns = ["showId"],childColumns = ["showId"])],
    indices = [Index(value = ["genreId"])])
data class GenreTvEntity(
    @NonNull
    @ColumnInfo(name = "genreId")
    var genreId: String,

    @NonNull
    @ColumnInfo(name = "showId")
    var movieId: String,

    @NonNull
    @ColumnInfo(name = "name")
    var name: String
)
