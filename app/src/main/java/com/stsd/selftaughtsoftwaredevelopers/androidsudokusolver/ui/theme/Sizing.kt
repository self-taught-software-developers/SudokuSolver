package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import javax.annotation.concurrent.Immutable

@Immutable
data class Sizing(
    val default: Dp = 0.dp,
    val xx_small: Dp = 2.dp,
    val x_small: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 24.dp,
    val x_large: Dp = 36.dp,
    val xx_large: Dp = 48.dp,
    val xxx_large: Dp = 60.dp
)

internal val LocalPadding = staticCompositionLocalOf { Sizing() }
internal val LocalSizing = staticCompositionLocalOf { Sizing() }
internal val LocalElevation = staticCompositionLocalOf { Sizing() }