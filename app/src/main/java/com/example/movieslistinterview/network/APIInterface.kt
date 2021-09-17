package com.example.movieslistinterview.network

import com.example.movieslistinterview.data.models.MovieDetailResponse
import com.example.movieslistinterview.data.models.MoviesListresponse
import retrofit2.Call
import retrofit2.http.*
import java.util.HashMap

interface APIInterface {

    @GET("upcoming?")
    fun moviesList(@QueryMap params: HashMap<String, String>): Call<MoviesListresponse?>?


    @GET("{movie-id}?")
    fun moviesListDetail(
        @Path("movie-id") movieId: String,
        @QueryMap params: HashMap<String, String>
    ): Call<MovieDetailResponse?>?
}