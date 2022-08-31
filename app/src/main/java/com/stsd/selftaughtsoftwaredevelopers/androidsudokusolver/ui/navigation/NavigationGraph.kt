package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ClearAll
import androidx.compose.material.icons.outlined.SelectAll
import androidx.compose.material.icons.outlined.Undo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.DefaultBottomBar
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.DefaultFab
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.DefaultTopBar
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.FabItem
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.navigation.NavigationRoutes.SCANNER_DESTINATION
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.navigation.NavigationRoutes.SOLVER_DESTINATION
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen.SudokuScannerScreen
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen.SudokuSolverScreen
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.viewmodel.SudokuViewModel

@Composable
fun SudokuSolverApp(
    vm: SudokuViewModel = viewModel()
) {

    val navController = rememberNavController()

    val state by vm.sudokuBoardStateAlt.collectAsState()
    val cameraState by vm.scannerState.collectAsState()

    val solved by state.solved.collectAsState()
    val board by state.initialBoard.collectAsState()
    val selected by state.selectedPosition.collectAsState()

    Scaffold(
        topBar = {
            DefaultTopBar(
                cameraState = cameraState,
                toggleCamera = vm::toggleCameraState
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            DefaultFab(
                fabItems = listOf(
                    FabItem(Icons.Outlined.Undo) { vm.undoLast() },
                    FabItem(Icons.Outlined.ClearAll) { vm.clearBoard() }
                )
            )
        },
        bottomBar = {
             DefaultBottomBar(
                onSolveBoard = vm::solveBoard
            ) { number ->
                vm.enterNewValue(number)
            }
        }
    ) { bounds ->

        NavHost(
            modifier = Modifier.padding(bounds),
            navController = navController,
            startDestination = SOLVER_DESTINATION
        ) {

            composable(SOLVER_DESTINATION) {


                SudokuSolverScreen(
                    cameraState = cameraState,
                    board = board,
                    dimens = state.dimensions.third,
                    updateSelectionPosition = vm::updateSelectedPosition
                )
            }

            composable(SCANNER_DESTINATION) {
                SudokuScannerScreen()
            }

        }

    }


}