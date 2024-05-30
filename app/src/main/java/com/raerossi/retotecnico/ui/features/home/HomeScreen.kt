package com.raerossi.retotecnico.ui.features.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.raerossi.retotecnico.domain.model.Movie
import com.raerossi.retotecnico.ui.components.SetSystemColors
import com.raerossi.retotecnico.ui.features.detail.DetailBottomSheet
import com.raerossi.retotecnico.ui.navigation.Screen

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    SetSystemColors(colorStatusBar = Color(0xFF2B292B))

    val moviesList: LazyPagingItems<Movie> = viewModel.moviesState.collectAsLazyPagingItems()
    HomeContent(movieList = moviesList)
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    movieList: LazyPagingItems<Movie>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF2B292B))
    ) {
        MovieList(movieList = movieList)
        DetailBottomSheet()
    }
}