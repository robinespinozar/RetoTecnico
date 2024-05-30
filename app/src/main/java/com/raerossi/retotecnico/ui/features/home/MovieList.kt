package com.raerossi.retotecnico.ui.features.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.paging.compose.items
import com.raerossi.retotecnico.domain.model.Movie
import com.raerossi.retotecnico.ui.features.detail.DetailViewModel

@Composable
fun MovieList(
    viewModel: DetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    movieList: LazyPagingItems<Movie>
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = movieList.loadState) {
        if (movieList.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (movieList.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .background(Color(0xFF2B292B))
    ) {
        if (movieList.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                color = Color(0xFF73D3B0),
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(movieList) { movie ->
                    if (movie != null) {
                        MovieItem(movie = movie) {
                            viewModel.openBottomSheet(it)
                        }
                    }
                }
                item {
                    if (movieList.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(color =  Color(0xFF73D3B0))
                    }
                }
            }
        }
    }

}