package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import androidx.compose.material.icons.rounded.Snooze
import androidx.compose.ui.graphics.vector.ImageVector
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.icon.TimeAuto
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.icon.Timer10Alt1
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.icon.Timer3Alt1
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.icon.rounded

enum class TimeState(val time: Long, val icon: ImageVector) {

    INSTANT_SPEED(10L, Timer3Alt1),
    SUPER_SPEED(50L, Timer10Alt1),
    DEFAULT_SPEED(200L, TimeAuto),
    SLOW_SPEED(800L, rounded.Snooze)
}
