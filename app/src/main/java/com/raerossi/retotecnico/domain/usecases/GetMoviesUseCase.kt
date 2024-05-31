package com.raerossi.retotecnico.domain.usecases

import android.util.Log
import androidx.paging.PagingData
import androidx.paging.log
import com.raerossi.retotecnico.data.MovieRepository
import com.raerossi.retotecnico.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(): Flow<PagingData<Movie>> {
        //Aqui deberiamos usar la clase NetworkUtils para verificar si hay conexion a internet
        //y si no la hay, retornar los datos de la base de datos local mediante Room
        //sino se devuelve normalmente los datos de la API

        return repository.getMoviesPageFromApi()
    }
}