package com.example.myapplicationjetpack.di

import android.content.Context
import com.example.myapplicationjetpack.data.source.MarvelRepository
import com.example.myapplicationjetpack.data.source.remote.RemoteDataSource
import com.example.myapplicationjetpack.source.local.LocalDataSource
import com.example.myapplicationjetpack.source.local.room.FilmDB
import com.example.myapplicationjetpack.utils.AppExecutors
import com.example.myapplicationjetpack.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): MarvelRepository {

        val database = FilmDB.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.filmDao())
        val appExecutors = AppExecutors()

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return MarvelRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

}