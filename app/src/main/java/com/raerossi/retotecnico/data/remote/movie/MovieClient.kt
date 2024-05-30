package com.raerossi.retotecnico.data.remote.movie

import com.raerossi.retotecnico.data.remote.movie.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieClient {

    @GET("upcoming")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieResponse>
}