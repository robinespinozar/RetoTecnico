package com.raerossi.retotecnico.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.raerossi.retotecnico.data.local.dao.MovieDao
import com.raerossi.retotecnico.data.local.entities.MovieEntity
import com.raerossi.retotecnico.data.mappers.toDatabase
import com.raerossi.retotecnico.data.mappers.toDomain
import com.raerossi.retotecnico.data.remote.movie.MovieService
import com.raerossi.retotecnico.data.remote.movie.paging.MoviePagingSource
import com.raerossi.retotecnico.domain.model.Movie
import com.raerossi.retotecnico.utils.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieService,
    private val dao: MovieDao
) {
    suspend fun getMoviesPageFromApi(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGING_CONFIG,
                prefetchDistance = 2
            ),
            pagingSourceFactory = { MoviePagingSource(api)  }
        ).flow
    }

}