package com.responsi.bacain.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val AnimeColorScheme = darkColorScheme(
    primary          = AnimeViolet,
    onPrimary        = AnimeOnPrimary,
    primaryContainer = AnimeDarkCard,
    secondary        = AnimeCyan,
    onSecondary      = AnimeNavy,
    tertiary         = AnimePink,
    background       = AnimeNavy,
    surface          = AnimeMidnight,
    surfaceVariant   = AnimeDarkCard,
    onBackground     = AnimeOnSurface,
    onSurface        = AnimeOnSurface,
    onSurfaceVariant = AnimeOnSurfaceVar,
)

@Composable
fun BacaInTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AnimeColorScheme,
        typography  = Typography,
        content     = content
    )
}