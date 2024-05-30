package com.raerossi.retotecnico.ui.features.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.raerossi.retotecnico.R
import com.raerossi.retotecnico.ui.components.SetSystemColors
import com.raerossi.retotecnico.ui.theme.backgroundGradient
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(toLoginScreen: () -> Unit) {
    SetSystemColors(colorStatusBar = Color(0xFF51B8BD))
    val scale = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.8f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )
        delay(1300L)
        toLoginScreen()
    }
    SplashScreen(scale = scale.value)
}

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    scale: Float
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = MaterialTheme.colorScheme.backgroundGradient),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .scale(scale)
                .padding(all = 64.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "splash logo"
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(scale = 0f)
}