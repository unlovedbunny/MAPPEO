package com.mappeo.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val MappeoColorScheme = lightColorScheme(
    primary          = Green800,
    onPrimary        = White,
    primaryContainer = Green100,
    onPrimaryContainer = Green900,
    secondary        = Green500,
    onSecondary      = White,
    background       = Green100,
    onBackground     = Gray700,
    surface          = White,
    onSurface        = Gray700,
    surfaceVariant   = Green100,
    error            = ErrorRed,
)

@Composable
fun MappeoTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = android.graphics.Color.TRANSPARENT
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }

    MaterialTheme(
        colorScheme = MappeoColorScheme,
        typography  = MappeoTypography,
        content     = content
    )
}