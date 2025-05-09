package com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board

import com.cerve.development.ui.canvas.model.CervePosition

fun findEmptyPosition(board: List<TileState>): CervePosition? {
    return board.find { tile -> tile.value == 0 }?.position
}
fun isInRow(
    candidate: TileState,
    board: List<TileState>
) : Boolean {
    return board.any { tile ->
        candidate.position.row == tile.position.row
                && candidate.value == tile.value
    }
}

fun isInColumn(
    candidate: TileState,
    board: List<TileState>
) : Boolean {
    return board.any { tile ->
        candidate.position.column == tile.position.column
                && candidate.value == tile.value
    }
}

fun isInSubgrid(
    candidate: TileState,
    board: List<TileState>
) : Boolean {
    return board.any { tile ->
        candidate.position.subgrid == tile.position.subgrid
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