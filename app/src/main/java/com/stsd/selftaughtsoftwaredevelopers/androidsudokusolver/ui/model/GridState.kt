package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import androidx.compose.material.icons.rounded.Grid3x3
import androidx.compose.material.icons.rounded.Grid4x4
import androidx.compose.material.icons.rounded.Window
import androidx.compose.ui.graphics.vector.ImageVector
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.icon.rounded

enum class GridState(val multiplier: Int, val icon: ImageVector) {
    GRID_2X2(multiplier = 2, rounded.Window),
    GRID_3X3(multiplier = 3, rounded.Grid3x3),
    GRID_4X4(multiplier = 4, rounded.Grid4x4)
}