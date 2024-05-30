package com.raerossi.retotecnico.data.remote.movie

import com.raerossi.retotecnico.data.remote.movie.model.MovieModel
import com.raerossi.retotecnico.data.remote.movie.response.MovieResponse
import com.raerossi.retotecnico.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(private val movieClient: MovieClient) {

    suspend fun getMovies(page: Int): MovieResponse {
        return withContext(Dispatchers.IO) {
            val response = movieClient.getMovies(page, Constants.API_KEY)
            response.body()!!
        }
    }
}