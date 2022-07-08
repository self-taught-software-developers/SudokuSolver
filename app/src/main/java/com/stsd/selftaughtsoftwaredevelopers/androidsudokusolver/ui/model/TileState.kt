package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

data class TileState(
    var text: String = EMPTY_TILE
) {
    companion object {
        const val EMPTY_TILE = ""

        val sudokuBoardArray = (1..9).map {
            (1..9).map { TileState() }.toTypedArray()
        }.toTypedArray()

        val sudokuBoardFilledArray = (1..9).map {
            (1..9).map { TileState(it.toString()) }.toTypedArray()
        }.toTypedArray()

    }
}