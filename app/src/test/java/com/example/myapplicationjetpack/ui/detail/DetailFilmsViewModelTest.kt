@file:Suppress("DEPRECATION")

package com.example.myapplicationjetpack.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.myapplicationjetpack.DataStatic
import com.example.myapplicationjetpack.data.source.MarvelRepository
import com.example.myapplicationjetpack.data.source.local.entity.MovieEntity
import com.example.myapplicationjetpack.data.source.local.entity.TvShowEntity
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DetailFilmsViewModelTest {
    private lateinit var filmsViewModel: DetailFilmsViewModel
    private val dataStaticMovie = DataStatic.generateStaticMovie()[0]
    private val dataStaticTvShow = DataStatic.generateStaticTvShow()[0]
    private val movie_id = dataStaticMovie.movie_id
    private val tvshow_id = dataStaticTvShow.tvshow_id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: MarvelRepository

    @Mock
    private lateinit var DetailMovieObserver: Observer<MovieEntity>

    @Mock
    private lateinit var DetailTvShowObserver: Observer<TvShowEntity>


    @Before
    fun setUp() {
        filmsViewModel = DetailFilmsViewModel(catalogueRepository)
        filmsViewModel.setSelectedMovie(movie_id)
        filmsViewModel.setSelectedTvShow(tvshow_id)
    }

    @Test
    fun getDetail() {
        val detailMovie = MutableLiveData<MovieEntity>()
        detailMovie.value = dataStaticMovie

        Mockito.`when`(catalogueRepository.getMoviesDetail(movie_id)).thenReturn(detailMovie)
        val movieEntity = filmsViewModel.getDetail().value as MovieEntity
        Mockito.verify(catalogueRepository).getMoviesDetail(movie_id)
        Assert.assertNotNull(movieEntity)
        assertEquals(dataStaticMovie.movie_id, movieEntity.movie_id)
        assertEquals(dataStaticMovie.release_date, movieEntity.release_date)
        assertEquals(dataStaticMovie.description, movieEntity.description)
        assertEquals(dataStaticMovie.img_poster, movieEntity.img_poster)
        assertEquals(dataStaticMovie.title, movieEntity.title)

        filmsViewModel.getDetail().observeForever(DetailMovieObserver)
        verify(DetailMovieObserver).onChanged(dataStaticMovie)
    }


    @Test
    fun getDetailTvShow() {
        val detailTvShow = MutableLiveData<TvShowEntity>()
        detailTvShow.value = dataStaticTvShow

        Mockito.`when`(catalogueRepository.getTvShowDetail(tvshow_id)).thenReturn(detailTvShow)
        val tvShowEntity = filmsViewModel.getDetailTvShow().value as TvShowEntity
        Mockito.verify(catalogueRepository).getTvShowDetail(tvshow_id)
        Assert.assertNotNull(tvShowEntity)
        assertEquals(dataStaticTvShow.tvshow_id, tvShowEntity.tvshow_id)
        assertEquals(dataStaticTvShow.release_date, tvShowEntity.release_date)
        assertEquals(dataStaticTvShow.description, tvShowEntity.description)
        assertEquals(dataStaticTvShow.img_poster, tvShowEntity.img_poster)
        assertEquals(dataStaticTvShow.title, tvShowEntity.title)

        filmsViewModel.getDetailTvShow().observeForever(DetailTvShowObserver)
        verify(DetailTvShowObserver).onChanged(dataStaticTvShow)
    }

}