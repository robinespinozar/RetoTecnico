package com.raerossi.retotecnico.domain.usecases

import androidx.paging.PagingData
import com.raerossi.retotecnico.data.MovieRepository
import com.raerossi.retotecnico.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(): Flow<PagingData<Movie>> {
        return repository.getMoviesPageFromApi()
    }
}