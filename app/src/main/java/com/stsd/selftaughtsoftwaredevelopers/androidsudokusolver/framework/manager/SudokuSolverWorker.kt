package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager

import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.toTileText
import kotlinx.coroutines.delay
import java.util.stream.IntStream.empty
import java.util.stream.IntStream.range
import javax.inject.Inject

class SudokuSolverWorker @Inject constructor() {

    suspend fun solveBoard(boardState: BoardState) {

        if (boardState.isValid().also { println(it) }) {
            findSolution(boardState)
        }

    }

    suspend fun findSolution(board: BoardState) : Array<Array<Int>> {

        val position = findEmptyPosition(board.fromUiBoard())

        if (position.isEmpty()) {
            return board.fromUiBoard()
        } else {

            (1..9).forEach { candidate ->

                if (validatePlacement(board.fromUiBoard(), candidate, position)) {
                    board.selectPosition(Pair(position[0],position[1]))
                    delay(80)
                    board.changeValue(toTileText(candidate))

                    if (findEmptyPosition(findSolution(board)).isEmpty()) {
                        return board.apply {
                            solved = true
                        }.fromUiBoard()
                    }

                    board.selectPosition(Pair(position[0],position[1]))
                    delay(80)
                    board.changeValue(toTileText(0))
                }

            }

            return board.fromUiBoard()

        }

    }

    private fun validatePlacement(
        board: Array<Array<Int>>,
        number: Int,
        position: List<Int>
    ): Boolean {
        // validate row
        if (board[position[0]].contains(number)) return false

        // validate column
        for (i in board) {
            if (i[position[1]] == number) return false
        }

        val x = position[1] / 3
        val y = position[0] / 3

        for (row in range(y*3, (y*3)+ 3)){
            for (column in range(x*3, (x*3)+3)){
                if (board[row][column] == number && listOf(row,column) != position) return false
            }
        }

        return true
    }

    private fun findEmptyPosition(board: Array<Array<Int>>): List<Int> {

        for (row in board) {
            for (column in row) {
                if (column == 0) return listOf(board.indexOf(row), row.indexOf(column))
            }
        }
        return emptyList()
    }

}