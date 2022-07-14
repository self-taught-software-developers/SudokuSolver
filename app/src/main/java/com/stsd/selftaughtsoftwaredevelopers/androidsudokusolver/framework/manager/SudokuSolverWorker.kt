package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager

import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState.Companion.convertToUiBoard
import java.util.stream.IntStream.range
import javax.inject.Inject

class SudokuSolverWorker @Inject constructor() {

    fun solveBoard(board: Array<Array<Int>>) : BoardState {

        if(board.isBoardNotValid()) return BoardState(board.convertToUiBoard(), false)

        return BoardState(board = findSolution(board).convertToUiBoard(), true)

    }

    fun findSolution(board: Array<Array<Int>>) : Array<Array<Int>> {

        val position = findEmptyPosition(board)

        if (position.isEmpty()) {
            return board
        } else {

            (1..9).forEach { candidate ->

                if (validatePlacement(board, candidate, position)) {

                    board[position[0]][position[1]] = candidate

                    if (findEmptyPosition(findSolution(board)).isEmpty()) return board

                    board[position[0]][position[1]] = 0
                }

            }

            return board

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

    companion object {

        fun Array<Array<Int>>.isBoardNotValid(): Boolean = !isBoardValid()
        fun Array<Array<Int>>.isBoardValid(): Boolean {
            // first verify if a row is valid. that's done by filtering all empty positions and calling a distinct on the board.
            // if the filtered board is not the same size as the distinct board then we know we have repeating values in our row.

            this.forEach { row ->
                row.filter { it != 0 }.also { noEmptyPositions ->
                    if (noEmptyPositions.distinct().size < noEmptyPositions.size) return false
                }
            }

            // to verify the column we need to  create a bucket per column and compare the items in that column to see if they contain any distinct values.
            var bucket = (0..this.lastIndex).map {
                arrayListOf<Int>()
            }

            this.forEach { row ->
                row.forEachIndexed { index, value ->
                    if (value != 0) bucket[index].add(value)
                }
            }

            bucket.forEach { values ->
                if (values.distinct().size < values.size) return false
            }

            bucket = (0..this.lastIndex).map {
                arrayListOf<Int>()
            }

            // to verify that our 3 by 3 grid doesn't have any repeating values we need ot view our 3 by 3 grid
            this.forEachIndexed { index, row ->

                val x = index / 3

                row.forEachIndexed { columnIndex, value ->
                    if (value != 0) {
                        val y = columnIndex / 3

                        when (listOf(x, y)) {
                            listOf(0, 0) -> bucket[0].add(value)
                            listOf(0, 1) -> bucket[1].add(value)
                            listOf(0, 2) -> bucket[2].add(value)
                            listOf(1, 0) -> bucket[3].add(value)
                            listOf(1, 1) -> bucket[4].add(value)
                            listOf(1, 2) -> bucket[5].add(value)
                            listOf(2, 0) -> bucket[6].add(value)
                            listOf(2, 1) -> bucket[7].add(value)
                            listOf(2, 2) -> bucket[8].add(value)
                        }
                    }
                }
            }

            bucket.forEach { values ->
                if (values.distinct().size < values.size) return false
            }

            return true

        }

    }
}