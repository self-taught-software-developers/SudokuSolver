package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager.SudokuSolverWorker
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.DefaultBottomBar
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.SudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.bordColor
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.AndroidSudokuSolverTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.viewmodel.SudokuViewModel

@Composable
fun SudokuScreen(
    modifier: Modifier = Modifier,
    vm: SudokuViewModel = viewModel()
) {
    val state by vm.sudokuBoardStateAlt.collectAsState()
    val selected by state.selectedPosition.collectAsState(initial = null)
    // TODO set a null state for the board.
    val board by state.initialBoard.collectAsState(BoardState.emptySudokuBoard(state.dimensions))

    Scaffold(
        modifier = modifier,
        bottomBar = {
            DefaultBottomBar(
                onUndoRecentChange = vm::undoLast,
                onClearBoard = vm::clearBoard,
                onSolveBoard = vm::solveBoard,
                onEnterNewValue = { number ->
                    vm.enterNewValue(number)
                }
            )
        }
    ) { bounds ->

        SudokuBoard(
            modifier = Modifier.padding(bounds),
            board = board,
            borderColor = state.solved.bordColor(),
            selectedPosition = selected?.let { (x, y) -> Triple(x, y, state.dimensions.third) }
        ) { newPosition ->
            vm.updateSelectedPosition(newPosition)
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SudokuScreenPreview() {
    AndroidSudokuSolverTheme {
        SudokuScreen(vm = SudokuViewModel(SudokuSolverWorker()))
    }
}
