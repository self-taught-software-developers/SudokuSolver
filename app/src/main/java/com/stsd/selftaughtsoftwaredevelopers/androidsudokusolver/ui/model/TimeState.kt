package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import androidx.compose.material.icons.rounded.Snooze
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material.icons.rounded.Timer10
import androidx.compose.material.icons.rounded.Timer3
import androidx.compose.ui.graphics.vector.ImageVector
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.rounded

enum class TimeState(val time: Long, val icon: ImageVector) {

    INSTANT_SPEED(10L, rounded.Timer3),
    SUPER_SPEED(50L, rounded.Timer10),
    DEFAULT_SPEED(200L, rounded.Timer),
    SLOW_SPEED(800L, rounded.Snooze)

}