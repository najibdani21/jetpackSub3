package com.example.movcatalog.di

import android.app.Application
import com.example.movcatalog.data.source.FilmRepository
import com.example.movcatalog.data.source.local.FilmDAO
import com.example.movcatalog.data.source.local.FilmDB
import com.example.movcatalog.data.source.local.LocalDataSource
import com.example.movcatalog.data.source.remote.RemoteDataSource
import com.example.movcatalog.data.source.remote.RetrofitAPI
import com.example.movcatalog.utils.ViewFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {
    companion object {
        @Singleton
        @Provides
        fun provideDatabase(application: Application): FilmDB =
            FilmDB.getInstance(application)

        @Singleton
        @Provides
        fun provideDao(filmDB: FilmDB): FilmDAO =
            filmDB.filmDao()

        @Singleton
        @Provides
        fun provideLocalDataSource(filmDAO: FilmDAO): LocalDataSource =
            LocalDataSource(filmDAO)

        @Singleton
        @Provides
        fun provideRemoteDataSource(retrofitAPI: RetrofitAPI): RemoteDataSource =
            RemoteDataSource(retrofitAPI)

        @Singleton
        @Provides
        fun provideRepository(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): FilmRepository = FilmRepository(remoteDataSource, localDataSource)

        @Singleton
        @Provides
        fun provideViewModelFactory(filmRepository: FilmRepository): ViewFactory =
            ViewFactory(filmRepository)

    }
}