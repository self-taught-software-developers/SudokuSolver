package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager

import java.util.stream.IntStream.range
import javax.inject.Inject

class SudokuSolverWorker @Inject constructor(

) {

    suspend fun solveBoard(board: Array<Array<Int>>) : Pair<Array<Array<Int>>, Boolean> {

        val position = findNextEmptyPosition(board)

        return if (position.first == -1) {
            board.map { it.contentToString() }.forEach(::println)
            Pair(board, true)
        } else {
            (1..9).forEach { number ->
                if(validateBoard(board, number, position)) {
                    board[position.first][position.second] = number

                    if (solveBoard(board).second) {
                        Pair(board, true)
                    }

                    board[position.first][position.second] = 0
                }
            }
            Pair(board, false)
        }

    }

    private fun findNextEmptyPosition(
        board: Array<Array<Int>>
    ) : Pair<Int, Int> {

        board.forEachIndexed { rowIndex, row ->

            row.forEachIndexed { columnIndex, column ->

                if (column == 0) return Pair(rowIndex, columnIndex)

            }

        }

        return Pair(-1, -1)

    }

    private fun validateBoard(
        board: Array<Array<Int>>,
        number: Int,
        position: Pair<Int, Int>
    ) : Boolean {

        //check row
        if (board[position.first].contains(number)) { return false }
        //check column
        for (i in board) {
            if (i[position.second] == number){ return false} }

        val cordX = position.second / 3
        val cordY = position.first / 3

        for (i in range(cordY*3, (cordY*3)+ 3)){
            for (j in range(cordX*3, (cordX*3)+3)){
                if (board[i][j] == number && Pair(i,j) != position) { return false }
            }
        }

        return true
        
    }

}