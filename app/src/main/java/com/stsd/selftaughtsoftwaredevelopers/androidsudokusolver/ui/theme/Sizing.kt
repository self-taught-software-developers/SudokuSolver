package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import javax.annotation.concurrent.Immutable

@Immutable
data class Sizing(
    val default: Dp = 0.dp,
    val xTiny: Dp = 2.dp,
    val tiny: Dp = 4.dp,
    val small: Dp = 8.dp,
    val large: Dp = 16.dp,
    val huge: Dp = 24.dp,
    val massive: Dp = 36.dp
)

internal val LocalPadding = staticCompositionLocalOf { Sizing() }
internal val LocalSizing = staticCompositionLocalOf { Sizing() }