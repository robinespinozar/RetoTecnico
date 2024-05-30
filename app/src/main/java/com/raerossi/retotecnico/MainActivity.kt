package com.raerossi.retotecnico

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.raerossi.retotecnico.ui.navigation.RetoTecnicoApp
import com.raerossi.retotecnico.ui.theme.RetoTecnicoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetoTecnicoTheme {
                RetoTecnicoApp()
            }
        }
    }
}