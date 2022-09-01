package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ClearAll
import androidx.compose.material.icons.outlined.Undo
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.*
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.GridState.*
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.IconItem
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState.*
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

    val dim by vm.gridDimension.collectAsState(GRID_3X3)
    val speed by vm.solutionSpeed.collectAsState(DEFAULT_SPEED)

    val state by vm.sudokuBoardStateAlt.collectAsState().apply {
        this.value.apply {
            dimensions = dim
            solutionSpeed = speed
        }
    }

    val cameraState by vm.scannerState.collectAsState()

    val showMoreItems = remember { mutableStateOf(listOf<IconItem>()) }


    Scaffold(
        topBar = {
            DefaultTopBar(
                timeState = speed,
                gridState = dim,
                cameraState = cameraState,
                toggleSolutionSpeed = {
                    if (showMoreItems.value.isEmpty()) {
                        showMoreItems.value = listOf(
                            IconItem(rounded.Timer3) { vm.updateSolutionSpeed(INSTANT_SPEED) },
                            IconItem(rounded.Timer10) { vm.updateSolutionSpeed(SUPER_SPEED) },
                            IconItem(rounded.Snooze) { vm.updateSolutionSpeed(SLOW_SPEED) },
                            IconItem(rounded.Timer) { vm.updateSolutionSpeed(DEFAULT_SPEED) }
                        )
                    } else { showMoreItems.value = listOf() }
                },
                toggleGridDimens = {
                    if (showMoreItems.value.isEmpty()) {
                        showMoreItems.value = listOf(
                            IconItem(Grid2x2) { vm.updateGridDimension(GRID_2X2) },
                            IconItem(rounded.Grid3x3) { vm.updateGridDimension(GRID_3X3) },
                            IconItem(rounded.Grid4x4) { vm.updateGridDimension(GRID_4X4) }
                        )
                    } else { showMoreItems.value = listOf() }
                },
                toggleCamera = vm::toggleCameraState
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            DefaultFab(
                fabItems = listOf(
                    IconItem(Icons.Outlined.Undo) { vm.undoLast() },
                    IconItem(Icons.Outlined.ClearAll) { vm.clearBoard() }
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
                    state = state,
                    updateSelectionPosition = vm::updateSelectedPosition
                )

                MoreOptionsBar(iconList = showMoreItems)

            }

        }

    }


}