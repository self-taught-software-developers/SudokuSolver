package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import androidx.compose.ui.graphics.Color
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.Purple200

data class Tile(
    var text: String = EMPTY_TILE,
    var state: TileState = TileState.VALID
) {
    companion object {
        const val EMPTY_TILE = ""
    }
}

val sudokuBoard = (1..9).map {
    (1..9).map { Tile() }
}

val fillSudokuBoard = (1..9).map {
    (1..9).map { Tile(text = it.toString()) }
}

enum class TileState(val color: Color? = null) {
    VALID,
    INVALID(color = Color.Red),
    SELECTED(color = Purple200.copy()),
    HIGHLIGHTED(color = Purple200.copy()),
}
