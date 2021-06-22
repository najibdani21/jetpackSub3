package com.example.myapplicationjetpack.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.myapplicationjetpack.data.source.MarvelRepository
import com.example.myapplicationjetpack.data.source.local.entity.MovieEntity
import com.example.myapplicationjetpack.data.source.local.entity.TvShowEntity
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val marvelRepository: MarvelRepository) : ViewModel() {

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> = marvelRepository.getFavoriteMovies()

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> = marvelRepository.getFavoriteTvShows()
}