package com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board

import androidx.compose.runtime.toMutableStateList
import com.cerve.development.ui.canvas.model.CerveCanvasState
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.component.findEmptyPosition
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.component.isValidPlacement
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.Position.Companion.findPosition
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.TimeState
import kotlinx.coroutines.delay

data class BoardState(
    val dimensions: GridState = GridState.GRID_3X3,
    val canvasState: CerveCanvasState
) {
    val board = List(dimensions.cellCount) { index ->
        TileState(
            point = index.findPosition(
                dimensions.multiplier,
                dimensions.vector
            ),
            origin = PlacementOrigin.solver
        )
    }.toMutableStateList()

    fun upsert(at: Int? = null, tile: TileState) {

        val index = at ?: board.indexOfFirst { element ->
            element.point == tile.point
        }

        if (index != -1) {
            board[index] = tile
        }
    }

    suspend fun delete() {
        board.forEachIndexed { index, tileState ->
            delay(TimeState.INSTANT_SPEED.time)
            upsert(at = index, tileState.copy(value = 0))
        }
    }

    suspend fun reset() {
        board.forEachIndexed { index, tileState ->
            delay(TimeState.INSTANT_SPEED.time)
            if (tileState.origin == PlacementOrigin.solver) {
                upsert(at = index, tileState.copy(value = 0)) }
        }
    }

    fun findSolution(board: MutableList<TileState>): List<TileState> {
        findEmptyPosition(board).also { position ->
            if (position == null) {
                return board
            } else {
                (1..9).forEach { value ->
                    val candidate = TileState(
                        value = value,
                        point = position,
                        origin = PlacementOrigin.solver
                    )
                    if (isValidPlacement(candidate, board)) {

                        val index = board.indexOfFirst { it.point == position }
                        upsert(index, candidate)

                        if (findEmptyPosition(findSolution(board)) == null) {
                            return board
                        }

                        upsert(index, candidate.copy(value = 0))

                    }
                }
                return board
            }
        }
    }

}