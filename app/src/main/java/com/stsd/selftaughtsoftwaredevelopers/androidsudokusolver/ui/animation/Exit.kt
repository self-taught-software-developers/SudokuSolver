package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.animation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically

fun exitOut() = slideOutVertically(
    // Exits by sliding up from offset 0 to -fullHeight.
    targetOffsetY = { fullHeight -> -fullHeight },
    animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
)