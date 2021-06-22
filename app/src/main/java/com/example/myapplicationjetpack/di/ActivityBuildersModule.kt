package com.example.myapplicationjetpack.di

import com.example.myapplicationjetpack.MainActivity
import com.example.myapplicationjetpack.MainBuildersModule
import com.example.myapplicationjetpack.ui.detail.DetailFilmsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [MainBuildersModule::class])
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun detailFilmsActivity(): DetailFilmsActivity

}