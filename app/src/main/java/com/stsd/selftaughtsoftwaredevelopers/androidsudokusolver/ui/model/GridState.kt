package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

enum class GridState(val multiplier: Int) {
    GRID_2X2(multiplier = 2),
    GRID_3X3(multiplier = 3),
    GRID_4X4(multiplier = 4)
}