package com.tuyp.mymovie.utils.di

import android.app.Application
import androidx.room.Room
import com.tuyp.mymovie.data.local.LocalDataSource
import com.tuyp.mymovie.data.local.room.MovieDao
import com.tuyp.mymovie.data.local.room.MyMovieDatabase
import com.tuyp.mymovie.data.remote.RemoteDataSource
import com.tuyp.mymovie.factory.DetailFactory
import com.tuyp.mymovie.factory.HomeFactory
import com.tuyp.mymovie.factory.ProfileFactory
import com.tuyp.mymovie.repository.DetailRepository
import com.tuyp.mymovie.repository.HomeRepository
import com.tuyp.mymovie.repository.ProfileRepository
import com.tuyp.mymovie.utils.AppExecutors
import com.tuyp.mymovie.utils.ConstVal
import com.tuyp.mymovie.utils.apiconfig.APIService
import com.tuyp.mymovie.view.fragment.MovieFragment
import com.tuyp.mymovie.view.fragment.TvShowFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ConstVal.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): APIService =
        retrofit.create(APIService::class.java)


    @Provides
    @Singleton
    fun provideRemoteData(apiService: APIService): RemoteDataSource =
        RemoteDataSource(apiService)

    @Provides
    @Singleton
    fun providDatabaseBuilder(app: Application): MyMovieDatabase =
        Room.databaseBuilder(app,MyMovieDatabase::class.java, "Movies.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideDao(movieDatabase: MyMovieDatabase): MovieDao =
        movieDatabase.movieDao()

    @Provides
    @Singleton
    fun provideLocalData(movieDao: MovieDao): LocalDataSource =
        LocalDataSource(movieDao)

    @Provides
    @Singleton
    fun provideHomeRepo(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, appExecutors: AppExecutors): HomeRepository =
        HomeRepository(remoteDataSource,localDataSource,appExecutors)

    @Provides
    @Singleton
    fun provideHomeFactory(homeRepository: HomeRepository): HomeFactory =
        HomeFactory(homeRepository)

    @Provides
    @Singleton
    fun provideDetailRepo(remoteDataSource: RemoteDataSource,localDataSource: LocalDataSource, appExecutors: AppExecutors): DetailRepository =
        DetailRepository(remoteDataSource,localDataSource,appExecutors)

    @Provides
    @Singleton
    fun provideDetailFactory(detailRepository: DetailRepository): DetailFactory =
        DetailFactory(detailRepository)

    @Provides
    @Singleton
    fun provideProfileFactory(profileRepository: ProfileRepository): ProfileFactory =
        ProfileFactory(profileRepository)

    @Provides
    @Singleton
    fun provideProfileRepo(localDataSource: LocalDataSource,appExecutors: AppExecutors): ProfileRepository =
        ProfileRepository(localDataSource,appExecutors)

}