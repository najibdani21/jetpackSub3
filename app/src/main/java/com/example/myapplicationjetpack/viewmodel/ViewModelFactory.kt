package com.example.myapplicationjetpack.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationjetpack.data.source.MarvelRepository
import com.example.myapplicationjetpack.di.Injection
import com.example.myapplicationjetpack.ui.detail.DetailFilmsViewModel
import com.example.myapplicationjetpack.ui.movie.MovieViewModel
import com.example.myapplicationjetpack.ui.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val catalogueRepository: MarvelRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(DetailFilmsViewModel::class.java) -> {
                DetailFilmsViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(catalogueRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}