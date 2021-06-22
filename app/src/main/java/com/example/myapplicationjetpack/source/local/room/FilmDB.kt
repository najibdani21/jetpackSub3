package com.example.myapplicationjetpack.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplicationjetpack.data.source.local.entity.MovieEntity
import com.example.myapplicationjetpack.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class FilmDB : RoomDatabase() {
    abstract fun filmDao(): FilmDAO

    companion object {
        @Volatile
        private var INSTANCE: FilmDB? = null

        fun getInstance(context: Context): FilmDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    FilmDB::class.java,
                    "filmDB"
                ).build()
            }
    }
}