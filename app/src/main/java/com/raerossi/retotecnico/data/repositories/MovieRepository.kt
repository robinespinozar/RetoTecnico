package com.raerossi.retotecnico.data.repositories

import com.raerossi.retotecnico.data.local.dao.MovieDao
import com.raerossi.retotecnico.data.mappers.toDomain
import com.raerossi.retotecnico.data.remote.movie.MovieService
import com.raerossi.retotecnico.domain.model.Movie
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieService,
    private val dao: MovieDao
) {

    suspend fun getMoviesFromApi(page: Int): List<Movie> {
        val response = api.getMovies(page)
        return response.map { it.toDomain() }
    }
}