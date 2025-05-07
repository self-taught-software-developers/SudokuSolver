package com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board

import com.cerve.development.ui.canvas.model.CerveCanvasState
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.component.findEmptyPosition
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.component.isValidPlacement
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.TimeState
import kotlinx.coroutines.delay

data class BoardState(
    val dimensions: GridDimensions = GridDimensions.GRID_3X3,
    val canvasState: CerveCanvasState
) {

    val board = dimensions.generateSudokuBoard()

    fun upsert(at: Int? = null, tile: TileState) {
        val index = at ?: board.indexOfFirst { element ->
            element.position == tile.position
        }

        if (index != -1) {
            board[index] = tile
        }
    }

    fun delete() {
        board.forEachIndexed { index, tileState ->
            upsert(at = index, tileState.copy(value = 0))
        }
    }

    fun reset() {
        board.forEachIndexed { index, tileState ->
            if (tileState.origin == PlacementOrigin.Solver) {
                upsert(at = index, tileState.copy(value = 0))
            }
        }
    }

    suspend fun findSolution(board: MutableList<TileState>): List<TileState> {
        delay(TimeState.INSTANT_SPEED.time)
        findEmptyPosition(board).also { position ->
            if (position == null) {
                return board
            } else {
                (1..9).forEach { value ->
                    val candidate = TileState(
                        value = value,
                        position = position,
                        origin = PlacementOrigin.Solver
                    )
                    if (isValidPlacement(candidate, board)) {

                        val index = board.indexOfFirst { it.position == position }
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