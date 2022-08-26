package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.Camera
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.SudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.bordColor
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.viewmodel.SudokuViewModel

@Composable
fun SudokuScannerScreen(vm: SudokuViewModel = viewModel()) {

    val state by vm.sudokuBoardStateAlt.collectAsState()

    val solved by state.solved.collectAsState()
    val board by state.initialBoard.collectAsState()
    val selected by state.selectedPosition.collectAsState()

    BoxWithConstraints {
        Camera()
        SudokuBoard(
            board = board,
            borderColor = solved.bordColor(),
            selectedPosition = selected?.let { (x,y) -> Triple(x,y, state.dimensions.third) }
        ) { newPosition ->
            vm.updateSelectedPosition(newPosition)
        }
    }

}