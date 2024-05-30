package com.raerossi.retotecnico.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

val ColorScheme.title: Color @Composable get() = md_title
val ColorScheme.description: Color @Composable get() = md_theme_ref_neutralVariant40
val ColorScheme.primary40: Color @Composable get() = md_theme_ref_primary40
val ColorScheme.primary50: Color @Composable get() = md_theme_ref_primary50
val ColorScheme.neutralVariant30: Color @Composable get() = md_theme_ref_neutralVariant30
val ColorScheme.neutralVariant40: Color @Composable get() = md_theme_ref_neutralVariant40
val ColorScheme.primary40_alpha40: Color @Composable get() = md_theme_ref_primary40_alpha40
val ColorScheme.neutral95: Color @Composable get() = md_theme_ref_neutral95
val ColorScheme.errorColor: Color @Composable get() = md_theme_error_color
val ColorScheme.primary: Color @Composable get() = md_primary
val ColorScheme.backgroundGradient: Brush @Composable get() = md_background_gradient
val ColorScheme.primaryGradient: Brush @Composable get() = md_primary_gradient
val ColorScheme.bottomSheetContainer: Brush @Composable get() = md_bottomsheet_gradient
val ColorScheme.onBottomSheetContainer: Color @Composable get() = md_theme_ref_neutral95

@Composable
fun RetoTecnicoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}