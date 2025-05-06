package com.stsd.selftaughtsoftwaredevelopers.shared.ui.model

import androidx.compose.ui.geometry.Offset

data class Position(
    val row: Int,
    val column: Int
) {
    val subgrid: Int = run {
        val subgridRow = row / 3
        val subgridCol = column / 3
        (subgridRow * 3) + subgridCol
    }

    val toOffset get() = Offset(x = column.toFloat(), y = row.toFloat())

}
