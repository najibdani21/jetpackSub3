package com.example.myapplicationjetpack.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.academies.vo.Resource
import com.example.myapplicationjetpack.data.source.local.entity.MovieEntity
import com.example.myapplicationjetpack.data.source.local.entity.TvShowEntity
import com.example.myapplicationjetpack.data.source.remote.RemoteDataSource
import com.example.myapplicationjetpack.data.source.remote.response.MovieResponse
import com.example.myapplicationjetpack.data.source.remote.response.TvShowResponse
import com.example.myapplicationjetpack.source.local.LocalDataSource
import com.example.myapplicationjetpack.source.remote.ApiResponse
import com.example.myapplicationjetpack.source.NetworkBoundResource
import com.example.myapplicationjetpack.utils.AppExecutors

class MarvelRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MarvelDataSource {

    companion object {
        @Volatile
        private var instance: MarvelRepository? = null
        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): MarvelRepository =
            instance ?: synchronized(this) {
                instance ?: MarvelRepository(remoteData, localData, appExecutors).apply { instance = this }
            }
    }

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            public override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val course = MovieEntity(response.id,
                        response.title,
                        response.description,
                        response.release_date,
                        response.img_poster)
                    movieList.add(course)
                }

                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getAllTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvShow()

            public override fun saveCallResult(data: List<TvShowResponse>) {
                val tvshowList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val course = TvShowEntity(response.id,
                        response.title,
                        response.description,
                        response.release_date,
                        response.img_poster)
                    tvshowList.add(course)
                }

                localDataSource.insertTvShows(tvshowList)
            }
        }.asLiveData()
    }

    override fun getMoviesDetail(movie_id: String): LiveData<PagedList<MovieEntity>> = localDataSource.getDetailMovie(movie_id)

    override fun getTvShowDetail(tvshow_id: String): LiveData<PagedList<TvShowEntity>> = localDataSource.getDetailTvShow(tvshow_id)

    override fun setFavoriteMovie(movie: MovieEntity) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie) }
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvShow)
        }
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }
}