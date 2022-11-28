package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Wine200,
    primaryVariant = Wine700,
    secondary = SeaWeedBlue200
)

private val LightColorPalette = lightColors(
    primary = Wine500,
    primaryVariant = Wine700,
    secondary = SeaWeedBlue200,

    background = Beige200

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
fun AndroidSudokuSolverTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    val systemUiController = rememberSystemUiController()

    LaunchedEffect(colors) {
        systemUiController.setSystemBarsColor(
            color = colors.background,
            darkIcons = !darkTheme
        )
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
