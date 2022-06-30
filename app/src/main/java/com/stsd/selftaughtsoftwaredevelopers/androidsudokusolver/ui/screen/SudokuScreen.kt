package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.SudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.TwoRowsOfButtonsOffset
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.AndroidSudokuSolverTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.viewmodel.SudokuViewModel

@Composable
fun SudokuScreen(
    vm: SudokuViewModel = viewModel()
) {

    val board = vm.sudokuBoardState
    val position by vm.selectedPosition.collectAsState(null)

    Scaffold(
        bottomBar = {
            TwoRowsOfButtonsOffset{
                vm.enterNewValue(it)
            }
        }
    ) { bounds ->

        SudokuBoard(
            modifier = Modifier.padding(bounds),
            board = board.first(),
            selectedPosition = position
        ) { newPosition ->
            vm.updateSelectedPosition(newPosition)
        }

    }

}

@Preview
@Composable
fun SudokuScreenPreview() {

    AndroidSudokuSolverTheme {
        SudokuScreen()
    }

}