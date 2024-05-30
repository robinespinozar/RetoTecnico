package com.raerossi.retotecnico.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.raerossi.retotecnico.ui.theme.neutralVariant30
import com.raerossi.retotecnico.ui.theme.primary40
import com.raerossi.retotecnico.ui.theme.title

@Composable
fun ErrorDialog(
    title: String = "Advertisement",
    message: String = "An unexpected error has occurred. Try Again.",
    onDissmis: () -> Unit
) {
    AlertDialog(
        title = { Text(text = title, style = MaterialTheme.typography.titleLarge) },
        text = { Text(text = message, style = MaterialTheme.typography.bodyMedium) },
        titleContentColor = MaterialTheme.colorScheme.title,
        textContentColor = MaterialTheme.colorScheme.neutralVariant30,
        containerColor = Color(0xFFFFFFFF),
        onDismissRequest = { onDissmis() },
        confirmButton = {
            TextButton(
                onClick = { onDissmis() },
                colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary40)
            ) {
                Text(
                    text = "Continue",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    )
}