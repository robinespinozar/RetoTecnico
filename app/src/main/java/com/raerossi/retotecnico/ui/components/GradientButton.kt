package com.raerossi.retotecnico.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raerossi.retotecnico.ui.navigation.RetoTecnicoApp
import com.raerossi.retotecnico.ui.theme.RetoTecnicoTheme
import com.raerossi.retotecnico.ui.theme.primaryGradient

@Composable
fun GradientButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.White,
    gradient: Brush,
    height: Int = 48,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .height(height.dp)
            .fillMaxWidth(),
        enabled = enabled,
        contentPadding = PaddingValues(),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = Color(0xFFE8F5F1)
        )
    ) {
        Box(
            modifier = if (enabled) Modifier
                .fillMaxSize()
                .background(gradient)
                 else Modifier,
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                color = if (enabled) textColor else Color(0xFF012015),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonsScreenPreview() {
    RetoTecnicoTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            GradientButton(
                text = "Log In",
                textColor = Color.White,
                gradient = MaterialTheme.colorScheme.primaryGradient
            ) {}
        }
    }
}
