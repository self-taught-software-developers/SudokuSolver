package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.EMPTY_TILE

data class BoardState(
    val board: Array<Array<TileState>> = emptySudokuBoard,
    val solved: Boolean? = null
) {

    fun convertFromUiBoard() : Array<Array<Int>> {
        return board.map { row ->
            row.map { value ->
                if(value.text == EMPTY_TILE) 0 else value.text.toInt()
            }.toTypedArray()
        }.toTypedArray()

    }

    companion object {

        fun Array<Array<Int>>.convertToUiBoard() : Array<Array<TileState>> {
            return this.map { row ->
                row.map { value ->
                    if(value == 0) TileState(EMPTY_TILE) else TileState(value.toString())
                }.toTypedArray()
            }.toTypedArray()
        }

        val emptySudokuBoard = (1..9).map {
            (1..9).map { TileState() }.toTypedArray()
        }.toTypedArray()

        val sudokuBoardFilled = (1..9).map {
            (1..9).map { TileState(it.toString()) }.toTypedArray()
        }.toTypedArray()
    }

}