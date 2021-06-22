package com.example.myapplicationjetpack.source.remote

import com.example.myapplicationjetpack.data.source.remote.response.MovieResponse
import com.example.myapplicationjetpack.data.source.remote.response.TvShowResponse
import com.example.myapplicationjetpack.source.remote.response.FilmResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {
    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = "4fabea41a5506693009cdead536f1965"
    ) : Call<FilmResponse<MovieResponse>>

    @GET("tvshow/on_the_air")
    fun getTvShowOnTheAir(
        @Query("api_key") apiKey: String = "4fabea41a5506693009cdead536f1965"
    ) : Call<FilmResponse<TvShowResponse>>
}