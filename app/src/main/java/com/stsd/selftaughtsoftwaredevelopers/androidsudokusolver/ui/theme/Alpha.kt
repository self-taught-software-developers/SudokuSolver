package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import javax.annotation.concurrent.Immutable

@Immutable
data class Alpha(
    val default_0: Float = 0.0F,
    val small_10: Float = 0.1F,
    val medium_30 : Float = 0.3F,
    val large_60: Float = 0.6F,
    val none_100: Float = 1F
)

internal val LocalAlpha = staticCompositionLocalOf { Alpha() }