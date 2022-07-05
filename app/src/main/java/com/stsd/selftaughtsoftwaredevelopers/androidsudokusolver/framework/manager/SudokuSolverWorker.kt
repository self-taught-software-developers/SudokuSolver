package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager

import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState
import java.util.stream.IntStream.range
import javax.inject.Inject

class SudokuSolverWorker @Inject constructor() {

    fun solveBoard(board: Array<Array<Int>>) : BoardState {

        val position = findEmptyPosition(board)
        println(position)

        return if (position.isEmpty()) {
            BoardState(board, true)
        } else {
            for (i in 1..9) {
                if (validateBoard(board, i , position)) {
                    board[position[0]][position[1]] = i

                     if (solveBoard(board).solved) {
                        return BoardState(board, true)
                    }

                    board[position[0]][position[1]] = 0
                }
            }
            BoardState(board, false)

        }

    }

    private fun validateBoard(board: Array<Array<Int>>, number: Int, position: List<Int> ): Boolean {
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