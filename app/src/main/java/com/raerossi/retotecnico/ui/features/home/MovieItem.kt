package com.raerossi.retotecnico.ui.features.home

import android.graphics.drawable.Drawable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.raerossi.retotecnico.domain.model.Movie

@Composable
fun MovieItem(movie: Movie, onClick: (Movie) -> Unit) {
    Card(
        Modifier
            .height(305.dp)
            .clickable { onClick(movie) },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFF011614))
    ) {
        CardContent(movie = movie)
    }
}

@Composable
fun CardContent(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    Column(
        modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val painter = rememberAsyncImagePainter(movie.getImageUrl())
        val transition by animateFloatAsState(
            targetValue = if (painter.state is AsyncImagePainter.State.Success) 1f else 0f,
            label = "movie transition"
        )
        Box(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(transition)
            )
        }
        Divider(color = Color(0xFFE3E3E3), thickness = 1.dp)
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = movie.releaseDate,
            color = Color(0xFF576B74),
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            modifier = Modifier.padding(top = 2.dp, bottom = 8.dp),
            text = movie.title,
            color = Color(0xFF050C0E),
            style = MaterialTheme.typography.titleSmall
        )
    }
}
