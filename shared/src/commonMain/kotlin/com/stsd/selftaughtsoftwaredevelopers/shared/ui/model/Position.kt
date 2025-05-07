package com.stsd.selftaughtsoftwaredevelopers.shared.ui.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.sp

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

    companion object {
        fun Int.findPosition(multiplier: Int, vector : Int) : Position {
            return Position(
                row = this / vector,
                column = this % vector,
                multiplier = multiplier
            )
        }
    }
}
