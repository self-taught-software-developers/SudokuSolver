package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager.SudokuSolverWorker
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.DefaultBottomBar
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.SudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.filledBoard
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.AndroidSudokuSolverTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.viewmodel.SudokuViewModel

@Composable
fun SudokuScreen(vm: SudokuViewModel = viewModel()) {

    val board by vm.sudokuBoardStateAlt.collectAsState()
    val position by vm.selectedPosition.collectAsState(null)

    Scaffold(
        bottomBar = {
            DefaultBottomBar(
                onUndoRecentChange = vm::unDoRecentChange,
                onClearBoard = vm::clearBoard,
                onSolveBoard = vm::solveBoard
            ) { number ->
                vm.enterNewValue(number)
            }
        }
    ) { bounds ->

        SudokuBoard(
            modifier = Modifier.padding(bounds),
            board = board,
            selectedPosition = position
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