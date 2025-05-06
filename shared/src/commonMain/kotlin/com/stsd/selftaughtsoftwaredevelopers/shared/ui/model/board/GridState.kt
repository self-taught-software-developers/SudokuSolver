package com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board

import kotlin.math.pow

enum class GridState(val multiplier: Int) {
    GRID_2X2(multiplier = 2, ),
    GRID_3X3(multiplier = 3),
    GRID_4X4(multiplier = 4);

    fun vector() = this.multiplier.toDouble().pow(2).toInt()
}