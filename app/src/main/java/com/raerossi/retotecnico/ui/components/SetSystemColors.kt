package com.raerossi.retotecnico.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SetSystemColors(
    colorStatusBar: Color = Color(0xFFFFFFFF),
    colorNavigationBar: Color = Color(0xFFEFF3F4)
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(colorStatusBar)
        systemUiController.setNavigationBarColor(colorNavigationBar)
    }
}
