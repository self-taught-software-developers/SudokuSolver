package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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

object CustomTheme {
    val colors: Colors
        @Composable
        get() = MaterialTheme.colors

    val typography: Typography
        @Composable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes

    val sizing: Sizing
        @Composable
        get() = LocalSizing.current

    val padding: Sizing
        @Composable
        get() = LocalPadding.current
}

@Composable
fun AndroidSudokuSolverTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    val systemUiController = rememberSystemUiController()

    LaunchedEffect(colors) {
        systemUiController.setSystemBarsColor(
            color = colors.primary,
            darkIcons = !darkTheme
        )
    }

    CompositionLocalProvider(
        LocalPadding provides Sizing(),
        LocalSizing provides Sizing(),
        LocalElevation provides Sizing()
    ) {

        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )

    }

}