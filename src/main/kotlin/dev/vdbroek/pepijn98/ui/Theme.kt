package dev.vdbroek.pepijn98.ui

import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*

object ThemeState {
    var isDark by mutableStateOf(true)
}

private val DarkColorPalette = darkColors(
    primary = AppColors.blue200,
    primaryVariant = AppColors.blue700,
    secondary = AppColors.orange200,
    background = AppColors.darkBackground,
    surface = AppColors.darkSurface
)

private val LightColorPalette = lightColors(
    primary = AppColors.blue200,
    primaryVariant = AppColors.blue700,
    secondary = AppColors.orange200
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
