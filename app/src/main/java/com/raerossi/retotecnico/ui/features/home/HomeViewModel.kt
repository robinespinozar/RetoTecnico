package com.raerossi.retotecnico.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.raerossi.retotecnico.data.local.entities.MovieEntity
import com.raerossi.retotecnico.data.mappers.toDomain
import com.raerossi.retotecnico.domain.model.Movie
import com.raerossi.retotecnico.domain.usecases.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _moviesState: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(value = PagingData.empty())
    val moviesState: MutableStateFlow<PagingData<Movie>> get() = _moviesState

    init {
        viewModelScope.launch {
            getMoviesUseCase().distinctUntilChanged().cachedIn(viewModelScope)
                .collect {
                    _moviesState.value = it
                }
        }
    }
}