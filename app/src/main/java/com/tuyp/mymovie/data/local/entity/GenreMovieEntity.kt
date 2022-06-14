package com.tuyp.mymovie.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index


@Entity(tableName = "genremovieentites",
primaryKeys = ["genreId","movieId"],
foreignKeys = [ForeignKey(entity = MovieEntity::class, parentColumns = ["movieId"],childColumns = ["movieId"])],
indices = [Index(value = ["genreId"])])
data class GenreMovieEntity(
    @NonNull
    @ColumnInfo(name = "genreId")
    var genreId: String,

    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: String,

    @NonNull
    @ColumnInfo(name = "name")
    var name: String

)
