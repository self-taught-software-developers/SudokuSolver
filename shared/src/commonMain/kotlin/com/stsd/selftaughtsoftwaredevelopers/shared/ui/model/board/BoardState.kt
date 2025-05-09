package com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board

import com.cerve.development.data.result.CerveResult
import com.cerve.development.ui.canvas.model.CerveCanvasState

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

    fun findSolution(board: MutableList<TileState>): CerveResult<List<TileState>?> {
        return if (board.none { tile -> tile.origin == PlacementOrigin.UserError }) {
            val position = findEmptyPosition(board)

            if (position == null) {
                return CerveResult.Success(board)
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

                        findSolution(board).data?.let { board ->
                            if (findEmptyPosition(board) == null) {
                                return CerveResult.Success(board)
                            }
                        } ?: CerveResult.Error(
                            value = board,
                            errorMessage = ""
                        )

                        upsert(index, candidate.copy(value = 0))

                    }
                }
                return CerveResult.Success(board)
            }
        } else {
            CerveResult.Error(
                value = board,
                errorMessage = ""
            )
        }
    }

}