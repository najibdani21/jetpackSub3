package com.example.myapplicationjetpack.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplicationjetpack.data.source.MarvelRepository
import com.example.myapplicationjetpack.data.source.local.entity.MovieEntity
import com.example.myapplicationjetpack.data.source.local.entity.TvShowEntity

class DetailFilmsViewModel(private val catalogueRepository: MarvelRepository) : ViewModel() {
    private lateinit var movie_id: String
    private lateinit var tvShow_id: String

    fun setSelectedMovie(movie_id: String) {
        this.movie_id = movie_id
    }

    fun setSelectedTvShow(tvshow_id: String) {
        this.tvShow_id = tvshow_id
    }


    fun getDetail(): LiveData<MovieEntity> = catalogueRepository.getMoviesDetail(movie_id)
    fun getDetailTvShow(): LiveData<TvShowEntity> = catalogueRepository.getTvShowDetail(tvShow_id)
}

