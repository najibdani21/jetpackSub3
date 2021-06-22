package com.example.myapplicationjetpack.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.myapplicationjetpack.data.source.local.entity.MovieEntity
import com.example.myapplicationjetpack.data.source.local.entity.TvShowEntity
import com.example.myapplicationjetpack.source.local.room.FilmDAO

class LocalDataSource private constructor(private val filmDao: FilmDAO) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(academyDao: FilmDAO): LocalDataSource =
            INSTANCE ?: LocalDataSource(academyDao).apply {
                INSTANCE = this
            }

    }

    fun getMovies() : DataSource.Factory<String, MovieEntity> = filmDao.getMovies()

    fun getFavoriteMovies() : DataSource.Factory<String, MovieEntity> = filmDao.getFavoriteMovies()

    fun getDetailMovie(movieId: String) : LiveData<PagedList<MovieEntity>> = filmDao.getDetailMovieById(movieId)

    fun insertMovies(movies: List<MovieEntity>) = filmDao.insertMovies(movies)


    fun getTvShows() : DataSource.Factory<String, TvShowEntity> = filmDao.getTvShows()

    fun getFavoriteTvShows() : DataSource.Factory<String, TvShowEntity> = filmDao.getFavoriteTvShows()

    fun getDetailTvShow(tvShowId: String) : LiveData<PagedList<TvShowEntity>> = filmDao.getDetailTvById(tvShowId)

    fun insertTvShows(tvShows: List<TvShowEntity>) = filmDao.insertTvShows(tvShows)

    fun setFavoriteMovie(movie : MovieEntity) {
        movie.favState = !movie.favState
        filmDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(tvShow : TvShowEntity) {
        tvShow.favState = !tvShow.favState
        filmDao.updateTvShow(tvShow)
    }
}