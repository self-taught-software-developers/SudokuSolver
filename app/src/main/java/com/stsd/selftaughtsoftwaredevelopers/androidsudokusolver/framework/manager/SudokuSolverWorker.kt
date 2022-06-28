package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager

import javax.inject.Inject

class SudokuSolverWorker @Inject constructor(

) {

    fun solveBoard(board: List<MutableList<Int>>) : Pair<List<List<Int>>, Boolean> {

        val position = findNextEmptyPosition(board)

        return if (position.first == -1) {
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
        board: List<List<Int>>
    ) : Pair<Int, Int> {

        board.forEachIndexed { rowIndex, row ->

            row.forEachIndexed { columnIndex, column ->

                if (column == 0) return Pair(rowIndex, columnIndex)

            }

        }

        return Pair(-1, -1)

    }

    private fun validateBoard(
        board: List<List<Int>>,
        number: Int,
        position: Pair<Int, Int>
    ) : Boolean {
        
        if (board[position.first].contains(number)) return false
        
        board.forEach { row ->
            if (row[position.second] == number) return false
        }
        
        val x = position.second / 3
        val y = position.first / 3

        ((y*3)..(y*3 + 3)).forEach { gridColumn ->

            ((x*3)..(x*3 + 3)).forEach { gridRow ->
                
                if (board[gridColumn][gridRow] == number 
                    && Pair(gridColumn, gridRow) != position
                ) return false
                
            }
            
        }
        
        return true
        
    }

}