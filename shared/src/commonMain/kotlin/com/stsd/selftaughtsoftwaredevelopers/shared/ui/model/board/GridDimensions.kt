package com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.cerve.development.ui.canvas.model.CervePosition
import kotlin.math.pow
import kotlin.math.roundToInt

enum class GridDimensions(val multiplier: Int) {
    GRID_2X2(multiplier = 2),
    GRID_3X3(multiplier = 3),
    GRID_4X4(multiplier = 4);

    private val vector get() = multiplier.toFloat().pow(2).roundToInt()

    private fun findPositionFromIndex(
        index: Int
    ) : CervePosition {
        return CervePosition(
            row = index / vector,
            column = index % vector,
            subgridSize = multiplier
        )
    }

    fun generateSudokuBoard() : SnapshotStateList<TileState> {
        return List(vector * vector) { index ->
            TileState(
                position = findPositionFromIndex(index),
                origin = PlacementOrigin.Solver
            )
        }.toMutableStateList()
    }
}