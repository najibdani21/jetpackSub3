package com.example.myapplicationjetpack.ui.detail

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myapplicationjetpack.MainActivity
import com.example.myapplicationjetpack.R
import com.example.myapplicationjetpack.data.source.local.entity.MovieEntity
import com.example.myapplicationjetpack.data.source.local.entity.TvShowEntity
import com.example.myapplicationjetpack.databinding.ActivityDetailBinding
import com.example.myapplicationjetpack.databinding.ContentDetailBinding
import com.example.myapplicationjetpack.ui.favorite.FavoriteActivity
import com.example.myapplicationjetpack.ui.favorite.MovieFavoriteFragment
import com.example.myapplicationjetpack.ui.favorite.TvShowFavoriteFragment
import com.example.myapplicationjetpack.viewmodel.ViewModelFactory

class DetailFilmsActivity : AppCompatActivity() {

    private var isFavorite : Boolean = false

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TVSHOW = "extra_tvshow"
    }

    private lateinit var contentDetailBinding: ContentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        contentDetailBinding = activityDetailBinding.detailContent

        setContentView(activityDetailBinding.root)

        setSupportActionBar(activityDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailFilmsViewModel::class.java]
        val extras = intent.extras
        if (extras != null) {
            val movie_id = extras.getString(EXTRA_MOVIE)
            val tvshow_id = extras.getString(EXTRA_TVSHOW)

            if (movie_id != null) {
                activityDetailBinding.progressBar.visibility = View.VISIBLE
                activityDetailBinding.detailContent.scrollView2.visibility = View.INVISIBLE

                viewModel.setSelectedMovie(movie_id)
                viewModel.getDetail().observe(this, {
                    activityDetailBinding.progressBar.visibility = View.GONE
                    activityDetailBinding.detailContent.scrollView2.visibility = View.VISIBLE
                    detailMovie(it) })

                contentDetailBinding.tabFav.setOnClickListener {
                    if (isFavorite) {
                        activityDetailBinding.detailContent.tabFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                        cancelFavorite()
//                isFavorite = false
                    } else {
                        activityDetailBinding.detailContent.tabFav.setImageResource(R.drawable.ic_baseline_favorite_24)
                        addMovieFav(MovieEntity())
                    }
                }
            }
            else if (tvshow_id != null) {

                activityDetailBinding.progressBar.visibility = View.VISIBLE
                activityDetailBinding.detailContent.scrollView2.visibility = View.INVISIBLE

                viewModel.setSelectedTvShow(tvshow_id)
                viewModel.getDetailTvShow().observe(this, {
                    activityDetailBinding.progressBar.visibility = View.GONE
                    activityDetailBinding.detailContent.scrollView2.visibility = View.VISIBLE
                    detailTvShow(it) })

                contentDetailBinding.tabFav.setOnClickListener {
                    if (isFavorite) {
                        activityDetailBinding.detailContent.tabFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                        cancelFavorite()
//                isFavorite = false
                    } else {
                        activityDetailBinding.detailContent.tabFav.setImageResource(R.drawable.ic_baseline_favorite_24)
                        addTvShowFav(TvShowEntity())
                    }
                }
            }
        }
    }

    private fun detailMovie(movieEntity: MovieEntity) {
        contentDetailBinding.tvItemTitle.text = movieEntity.title
        contentDetailBinding.tvItemOverview.text = movieEntity.desc
        contentDetailBinding.tvItemRelease.text = movieEntity.date

        Glide.with(this)
            .load(movieEntity.poster)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loader)
                .error(R.drawable.ic_error))
            .into(contentDetailBinding.imgDetail)

        contentDetailBinding.tabFav.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            intent.putExtra(DetailFilmsActivity.EXTRA_MOVIE, movieEntity)
            startActivity(intent)
        }
    }

    private fun detailTvShow(tvShowEntity: TvShowEntity) {
        contentDetailBinding.tvItemTitle.text = tvShowEntity.title
        contentDetailBinding.tvItemOverview.text = tvShowEntity.desc
        contentDetailBinding.tvItemRelease.text = tvShowEntity.date

//        val movieFav = intent.getParcelableExtra<MovieEntity>(EXTRA_FAV) as TvShowEntity

        Glide.with(this)
            .load(tvShowEntity.poster)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loader)
                    .error(R.drawable.ic_error))
            .into(contentDetailBinding.imgDetail)

        contentDetailBinding.tabFav.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            intent.putExtra(DetailFilmsActivity.EXTRA_MOVIE, tvShowEntity)
            startActivity(intent)
        }
    }

    private fun addMovieFav(movie: MovieEntity){
//        val movieFav = intent.getParcelableExtra<MovieEntity>(EXTRA_FAV) as MovieEntity

        with(contentDetailBinding) {
            tvItemTitle.text = movie.title
            tvItemRelease.text = movie.date
            tabFav.setOnClickListener {
                val intent = Intent(tabFav.context, MovieFavoriteFragment::class.java)
                intent.putExtra(MovieFavoriteFragment.EXTRA_FAV, movie)
                tabFav.context.startActivity(intent)
            }

            Glide.with(tabFav.context)
                    .load(movie.poster)
                    .transform(RoundedCorners(20))
                    .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loader)
                                    .error(R.drawable.ic_error)
                    )
                    .into(imgDetail)
            }

        isFavorite = true
        Toast.makeText(this, "Added to Favorite", Toast.LENGTH_SHORT).show()
        tabFav(true)

    }

    private fun addTvShowFav(tvshow: TvShowEntity){
//        val movieFav = intent.getParcelableExtra<MovieEntity>(EXTRA_FAV) as MovieEntity

        with(contentDetailBinding) {
            tvItemTitle.text = tvshow.title
            tvItemRelease.text = tvshow.date
            tabFav.setOnClickListener {
                val intent = Intent(tabFav.context, TvShowFavoriteFragment::class.java)
                intent.putExtra(TvShowFavoriteFragment.EXTRA_FAV, tvshow)
                tabFav.context.startActivity(intent)
            }

            Glide.with(tabFav.context)
                    .load(tvshow.poster)
                    .transform(RoundedCorners(20))
                    .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loader)
                                    .error(R.drawable.ic_error)
                    )
                    .into(imgDetail)
            }

        isFavorite = true
        Toast.makeText(this, "Added to Favorite", Toast.LENGTH_SHORT).show()
        tabFav(true)

    }

    private fun cancelFavorite() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Cancel Favorite", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun tabFav(state: Boolean) {
        when(state){
            true -> contentDetailBinding.tabFav.setImageResource(R.drawable.ic_baseline_favorite_24)
            false -> contentDetailBinding.tabFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}