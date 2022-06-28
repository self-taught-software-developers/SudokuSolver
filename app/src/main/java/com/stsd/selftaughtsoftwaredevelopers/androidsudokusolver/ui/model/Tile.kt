package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import androidx.compose.ui.graphics.Color
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.Purple200

data class Tile(
    var value: String = " ",
    var state: TileState = TileState.VALID
)

val sudokuBoard = (1..9).map {
    (1..9).map { Tile() }
}

/**
 * as list [] holds our rows in on the board.
 * so all elements in our top level lists ranges from 1..9(9 positions in each board)
 *
 *
 *
 *
 */

enum class TileState(val color: Color? = null) {
    VALID,
    INVALID(color = Color.Red),
    SELECTED(color = Purple200.copy()),
    HIGHLIGHTED(color = Purple200.copy()),
}
