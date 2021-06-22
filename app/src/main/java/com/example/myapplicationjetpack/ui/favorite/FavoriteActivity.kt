package com.example.myapplicationjetpack.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplicationjetpack.PagerAdapter
import com.example.myapplicationjetpack.R
import com.example.myapplicationjetpack.databinding.ActivityFavoriteBinding
import com.example.myapplicationjetpack.databinding.ActivityMainBinding

class FavoriteActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FAV = "extra_fav"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)

        val sectionsPagerAdapter = PagerAdapterFavorite(this, supportFragmentManager)
        activityFavoriteBinding.viewPager.adapter = sectionsPagerAdapter
        activityFavoriteBinding.tabLayout.setupWithViewPager(activityFavoriteBinding.viewPager)

        supportActionBar?.elevation = 0f
    }
}