package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager

import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.emptySudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager.SudokuSolverWorker.Companion.isBoardValid
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.inValidColumnSudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.inValidGridSudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.inValidRowSudokuBoard
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

internal class SudokuSolverWorkerTest {

    private val solver = SudokuSolverWorker()

        @Test
    fun whenBoardContainsAnInvalidRowValidatorReturnsFalse() {

        solver.apply {
            assertFalse(inValidRowSudokuBoard.isBoardValid())
        }

    }

    @Test
    fun whenBoardContainsAnInvalidColumnValidatorReturnsFalse() {

        solver.apply {
            assertFalse(inValidColumnSudokuBoard.isBoardValid())
        }

    }

    @Test
    fun whenBoardContainsAnInvalidGridValidatorReturnsFalse() {

        solver.apply {
            assertFalse(inValidGridSudokuBoard.isBoardValid())
        }

    }

    @Test
    fun whenBoardIsEmptyAValidSolutionIsGeneratedRecursively() {

        solver.apply {

            assertTrue(findSolution(emptySudokuBoard).isBoardValid())

        }

    }

}