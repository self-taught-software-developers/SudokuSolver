package com.stsd.selftaughtsoftwaredevelopers.shared.ui.component

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke.Companion.DefaultMiter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.Position
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.TileState
import kotlin.math.pow
import kotlin.math.sqrt

fun findEmptyPosition(board: List<TileState>): Position? {
    return board.find { tile -> tile.value == 0 }?.point
}
fun isInRow(
    candidate: TileState,
    board: List<TileState>
) : Boolean {
    return board.any { tile ->
        candidate.point.row == tile.point.row
                && candidate.value == tile.value
    }
}

fun isInColumn(
    candidate: TileState,
    board: List<TileState>
) : Boolean {
    return board.any { tile ->
        candidate.point.column == tile.point.column
                && candidate.value == tile.value
    }
}

fun isInSubgrid(
    candidate: TileState,
    board: List<TileState>
) : Boolean {
    return board.any { tile ->
        candidate.point.subgrid == tile.point.subgrid
                && candidate.value == tile.value
    }
}

fun isValidPlacement(
    candidate: TileState,
    board: List<TileState>
) : Boolean {
    return !isInRow(candidate, board) &&
    !isInColumn(candidate, board) &&
    !isInSubgrid(candidate, board)
}