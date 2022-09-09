package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import javax.annotation.concurrent.Immutable

@Immutable
data class Sizing(
    val small_x2: Dp = 2.dp,
    val small_x1: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 24.dp,
    val large_x1: Dp = 36.dp,
    val large_x2: Dp = 48.dp,
    val large_x3: Dp = 60.dp,
    val default_top_bar: Dp = 56.dp,
    val default_icon_x2 : Dp = large_x2 * 2
)

internal val LocalPadding = staticCompositionLocalOf { Sizing() }
internal val LocalSizing = staticCompositionLocalOf { Sizing() }
internal val LocalElevation = staticCompositionLocalOf { Sizing() }