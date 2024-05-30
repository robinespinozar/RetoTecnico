package com.raerossi.retotecnico.ui.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raerossi.retotecnico.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie

    private val _isSheetOpen = MutableLiveData<Boolean>()
    val isSheetOpen: LiveData<Boolean> = _isSheetOpen

    private val _isSheetLoading = MutableLiveData<Boolean>()
    val isSheetLoading: LiveData<Boolean> = _isSheetLoading

    fun openBottomSheet(movie: Movie) {
        viewModelScope.launch {
            _isSheetOpen.value = true
            _isSheetLoading.value = true
            _movie.value = movie
            _isSheetLoading.value = false
        }
    }

    fun hideBottomSheet() {
        _isSheetOpen.value = false
    }
}