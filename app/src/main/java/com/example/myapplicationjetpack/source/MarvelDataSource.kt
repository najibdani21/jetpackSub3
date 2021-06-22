package com.example.myapplicationjetpack.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.academies.vo.Resource
import com.example.myapplicationjetpack.data.source.local.entity.MovieEntity
import com.example.myapplicationjetpack.data.source.local.entity.TvShowEntity

interface MarvelDataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getMoviesDetail(movie_id: String): LiveData<PagedList<MovieEntity>>

    fun getTvShowDetail(tvshow_id: String): LiveData<PagedList<TvShowEntity>>

    fun setFavoriteMovie(movie: MovieEntity)

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun setFavoriteTvShow(tvShow: TvShowEntity)

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>

}