@file:Suppress("UNCHECKED_CAST")

package com.example.myapplicationjetpack.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.myapplicationjetpack.DataStatic
import com.example.myapplicationjetpack.data.source.MarvelRepository
import com.example.myapplicationjetpack.data.source.local.entity.TvShowEntity
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: MarvelRepository


    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogueRepository)
    }

    @Test
    fun getTvShows() {
        val dataStatic = DataStatic.generateStaticTvShow()
        val tvShows = MutableLiveData<List<TvShowEntity>>()
        tvShows.value = dataStatic

        Mockito.`when`(catalogueRepository.getAllTvShow()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTvShows().value
        Mockito.verify(catalogueRepository).getAllTvShow()
        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(10, tvShowEntities?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dataStatic)
    }
}