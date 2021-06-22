package com.example.myapplicationjetpack.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ViewSwitcher
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationjetpack.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavoriteFragment : DaggerFragment() {
    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var factory: ViewSwitcher.ViewFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context?.let { setupViewPager(it) }
//        viewModel = ViewModelProvider(this@FavoriteFragment, factory)[FavoriteViewModel::class.java]
    }

    private fun setupViewPager(context: Context) {
        val pagerAdapter = PagerAdapterFavorite(context, childFragmentManager)
//        vp_fav.adapter = pagerAdapter
//        favTabs.setupWithViewPager(vp_fav)
    }

}