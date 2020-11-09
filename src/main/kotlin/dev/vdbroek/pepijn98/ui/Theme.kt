package dev.vdbroek.pepijn98.ui

import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*

object ThemeState {
    var isDark by mutableStateOf(true)
}

private val DarkColorPalette = darkColors(
    primary = blue200,
    primaryVariant = blue700,
    secondary = orange200,
    background = darkBackground,
    surface = darkSurface
)

private val LightColorPalette = lightColors(
    primary = blue200,
    primaryVariant = blue700,
    secondary = orange200
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    DesktopMaterialTheme (
        colors = if (ThemeState.isDark) DarkColorPalette else LightColorPalette,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
