package com.tuyp.mymovie.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tuyp.mymovie.data.local.entity.*


@Database(entities = [MovieEntity::class,
    TvShowEntity::class,
    MyFavoriteMovie::class,
    MyFavoriteTvShow::class,
    GenreMovieEntity::class,
    GenreTvEntity::class],
version = 1,
exportSchema = false)
abstract class MyMovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao

}