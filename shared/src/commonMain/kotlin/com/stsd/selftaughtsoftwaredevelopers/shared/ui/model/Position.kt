package com.stsd.selftaughtsoftwaredevelopers.shared.ui.model

import androidx.compose.ui.geometry.Offset
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.GridDimensions

data class Position(
    val row: Int,
    val column: Int,
    private val multiplier : Int
) {
    val subgrid get() : Int = run {
        val subgridRow = row / multiplier
        val subgridCol = column / multiplier
        (subgridRow * multiplier) + subgridCol
    }

    val toOffset get() = Offset(x = column.toFloat(), y = row.toFloat())

}
