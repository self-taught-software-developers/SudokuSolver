package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val DarkColorPalette = darkColorScheme(
    primary = Wine200,
//    primaryVariant = Wine700,
    secondary = SeaWeedBlue200,
    error = Color(red = 179, green = 38, blue = 30)
)

val LightColorPalette = lightColorScheme(
    primary = Wine500,
//    primaryVariant = Wine700,
    secondary = SeaWeedBlue200,
    background = Beige200,
    error = Color(red = 179, green = 38, blue = 30)
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)
