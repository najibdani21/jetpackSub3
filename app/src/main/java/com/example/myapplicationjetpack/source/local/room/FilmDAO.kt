package com.example.myapplicationjetpack.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.room.*
import com.example.myapplicationjetpack.data.source.local.entity.MovieEntity
import com.example.myapplicationjetpack.data.source.local.entity.TvShowEntity

@Dao
interface FilmDAO {
    @Query("SELECT * FROM tablefavMovie")
    fun getMovies() : DataSource.Factory<String, MovieEntity>

    @Query("SELECT * FROM tablefavTvShow")
    fun getTvShows() : DataSource.Factory<String, TvShowEntity>

    @Query("SELECT * FROM tablefavMovie WHERE favState = 1")
    fun getFavoriteMovies() : DataSource.Factory<String, MovieEntity>

    @Query("SELECT * FROM tablefavTvShow WHERE favState = 1")
    fun getFavoriteTvShows() : DataSource.Factory<String, TvShowEntity>

    @Query("SELECT * FROM tablefavMovie WHERE movieId = :movieId")
    fun getDetailMovieById(movieId: String) : LiveData<PagedList<MovieEntity>>

    @Query("SELECT * FROM tablefavTvShow WHERE tvShowId = :tvShowId")
    fun getDetailTvById(tvShowId: String) : LiveData<PagedList<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvShowEntity::class)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update(entity = MovieEntity::class)
    fun updateMovie(movie : MovieEntity)

    @Update(entity = TvShowEntity::class)
    fun updateTvShow(tvShows: TvShowEntity)
}