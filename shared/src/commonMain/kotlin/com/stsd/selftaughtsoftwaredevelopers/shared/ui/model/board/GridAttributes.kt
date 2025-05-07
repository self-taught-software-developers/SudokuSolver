package com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board

import kotlin.math.pow
import kotlin.math.roundToInt

enum class GridAttributes(val multiplier: Int) {
    GRID_2X2(multiplier = 2),
    GRID_3X3(multiplier = 3),
    GRID_4X4(multiplier = 4);

    val vector get() = multiplier.toFloat().pow(2).roundToInt()
    val cellCount get() = vector * vector
}