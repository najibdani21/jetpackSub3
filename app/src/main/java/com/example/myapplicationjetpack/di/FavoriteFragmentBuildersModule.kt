package com.example.myapplicationjetpack

import com.example.myapplicationjetpack.ui.favorite.MovieFavoriteFragment
import com.example.myapplicationjetpack.ui.favorite.TvShowFavoriteFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun movieFavoriteFragment() : MovieFavoriteFragment

    @ContributesAndroidInjector
    abstract fun tvShowFavoriteFragment() : TvShowFavoriteFragment
}