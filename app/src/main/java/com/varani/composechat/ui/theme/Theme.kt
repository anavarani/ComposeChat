package com.varani.composechat.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Pink,
    onPrimary = Color.White,
    primaryContainer = Gray,
    secondary = Orange,
    onSecondary = Color.White,
    tertiary = Color.White,
    onTertiary = Gray,
    tertiaryContainer = LightGray
)

private val LightColorPalette = lightColorScheme(
    primary = Pink,
    onPrimary = Color.White,
    primaryContainer = Color.White,
    secondary = Orange,
    onSecondary = Color.White,
    tertiary = Gray,
    onTertiary = Color.White,
    tertiaryContainer = LightGray

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ComposeChatTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}