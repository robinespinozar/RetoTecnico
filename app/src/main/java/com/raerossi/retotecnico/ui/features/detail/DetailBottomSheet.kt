package com.raerossi.retotecnico.ui.features.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.raerossi.retotecnico.R
import com.raerossi.retotecnico.domain.model.Movie
import com.raerossi.retotecnico.ui.theme.bottomSheetContainer
import com.raerossi.retotecnico.ui.theme.onBottomSheetContainer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailBottomSheet(
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val isSheetOpen by detailViewModel.isSheetOpen.observeAsState(false)
    val isSheetLoading by detailViewModel.isSheetLoading.observeAsState(false)
    val movie by detailViewModel.movie.observeAsState()

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if (isSheetOpen) {
        ModalBottomSheet(
            containerColor = Color(0xFF1C1B1E),
            onDismissRequest = { detailViewModel.hideBottomSheet() },
            sheetState = sheetState,
            dragHandle = {
                DragHandle { BottomSheetDefaults.DragHandle(color = Color(0xFF4C494F)) }
            }
        ) {
            ModalBottomSheetContent(
                movie = movie,
                isSheetLoading = isSheetLoading,
                onHideClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            detailViewModel.hideBottomSheet()
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun DragHandle(setDragHandle: @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        setDragHandle()
    }
}

@Composable
fun ModalBottomSheetContent(
    movie: Movie?,
    isSheetLoading: Boolean,
    onHideClick: () -> Unit
) {
    if (isSheetLoading) {
        LoadingSheet()
    } else {
        SheetContent(
            movie = movie,
            onHideClick = { onHideClick() }
        )
    }
}

@Composable
fun SheetContent(
    movie: Movie?,
    onHideClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(brush = MaterialTheme.colorScheme.bottomSheetContainer),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MovieImage(
            voteAverage = movie!!.voteAverage.toString(),
            imageUrl = movie.getImageUrl(),
            onHideClick = { onHideClick() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.title,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = movie.getRealeaseDateFormat(),
            color = Color(0xFFC7CCCA),
            style = MaterialTheme.typography.labelSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = movie.overview,
            color = Color(0xFFC7CCCA),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun LoadingSheet(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = MaterialTheme.colorScheme.bottomSheetContainer),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            Modifier
                .padding(top = 32.dp)
                .align(Alignment.TopCenter),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun MovieImage(
    voteAverage: String,
    modifier: Modifier = Modifier,
    imageUrl: String,
    onHideClick: () -> Unit
) {
    Column(
        modifier
            .fillMaxWidth()
    ) {
        SheetBar(voteAverage = voteAverage, onHideClick = { onHideClick() })
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .height(200.dp)
                .padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Fit,
            contentDescription = "movie image"
        )
    }
}

@Composable
fun SheetBar(voteAverage: String, onHideClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.Transparent),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier.padding(start = 8.dp),
            onClick = { onHideClick() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_down_arrow),
                contentDescription = "close button",
                tint = Color.Unspecified
            )
        }
        Calification(
            modifier = Modifier.padding(end = 8.dp),
            voteAverage = voteAverage
        )
    }
}

@Composable
fun Calification(modifier: Modifier = Modifier, voteAverage: String) {
    Row(
        modifier = modifier
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_favorite_star_filled),
                contentDescription = "favorite button",
                tint = Color.Unspecified
            )
        }
        Text(
            modifier = Modifier.padding(end = 8.dp),
            text = "$voteAverage/10",
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}