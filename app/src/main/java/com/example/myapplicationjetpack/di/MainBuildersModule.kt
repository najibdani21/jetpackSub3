package com.example.myapplicationjetpack

import com.example.myapplicationjetpack.ui.favorite.FavoriteFragment
import com.example.myapplicationjetpack.ui.movie.MovieFragment
import com.example.myapplicationjetpack.ui.tvshow.TvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainBuildersModule {

    @ContributesAndroidInjector
    abstract fun movieFragment() : MovieFragment

    @ContributesAndroidInjector
    abstract fun tvshowFragment() : TvShowFragment

    @ContributesAndroidInjector(modules = [FavoriteFragmentBuildersModule::class])
    abstract fun favoriteFragment() : FavoriteFragment
}