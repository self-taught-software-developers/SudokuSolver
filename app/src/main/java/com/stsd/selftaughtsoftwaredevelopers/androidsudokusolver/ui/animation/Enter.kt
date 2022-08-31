package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.animation

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically

fun enterIn() = slideInVertically(
    // Enters by sliding down from offset -fullHeight to 0.
    initialOffsetY = { fullHeight -> -fullHeight },
    animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
)