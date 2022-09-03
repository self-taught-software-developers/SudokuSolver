package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.*
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.GridState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.IconItem
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.DarkPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun SudokuSolverScreen(
    modifier: Modifier = Modifier,
    solutionSpeedState: Flow<TimeState>,
    updateSolutionSpeed: (TimeState) -> Unit
) {

    var enabled by rememberSaveable { mutableStateOf(true) }
    var isCameraOn by rememberSaveable { mutableStateOf(false) }
    var showMoreItems by remember { mutableStateOf<List<IconItem>?>(null) }

    val board by remember { mutableStateOf(BoardState(dimensions = GridState.GRID_3X3)) }

    //TODO INVESTIGATE WHETHER OR NOT THIS IS GOOD PRACTICE.
    val timeState by solutionSpeedState.collectAsState(TimeState.DEFAULT_SPEED)

    Scaffold(
        modifier = modifier,
        topBar = {
            DefaultTopBar(
                enabled = enabled,
                timeState = timeState,
                isCameraOn = isCameraOn,
                toggleCamera = { isCameraOn = !isCameraOn }
            ) {
                showMoreItems = showMoreItems?.let { null } ?: listOf(
                    IconItem(rounded.Timer3) { updateSolutionSpeed(TimeState.INSTANT_SPEED) },
                    IconItem(rounded.Timer10) { updateSolutionSpeed(TimeState.SUPER_SPEED) },
                    IconItem(rounded.Timer) { updateSolutionSpeed(TimeState.DEFAULT_SPEED) },
                    IconItem(rounded.Snooze) { updateSolutionSpeed(TimeState.SLOW_SPEED) }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            DefaultFab(
                enabled = enabled,
                items = listOf(
                    IconItem(rounded.Undo) { /*TODO clear the recently placed value on the sudoku board*/ },
                    IconItem(rounded.ClearAll) { /*TODO clear all values on the sudoku board*/ }
                )
            )
        },
        bottomBar = {
             DefaultBottomBar(
                 enabled = enabled,
                 onClickSolve = { /*TODO solve the sudoku board onClick*/ }
            ) { /*TODO onClick put value as selected tile value*/ }
        },
    ) { bounds ->

        MoreOptionsBar(iconList = showMoreItems) { showMoreItems = null }

        BoxWithConstraints(
            modifier = Modifier
                .padding(bounds)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            SudokuBoard(
                state = board.calculateTileDimensions(scope = this),
                cameraEnabled = isCameraOn,
                enabled = enabled
            ) { enabled = it }

        }

    }

}

@DarkPreview
@Composable
fun SudokuSolverScreenPreview() {
    SudokuSolverScreen(solutionSpeedState = flowOf(TimeState.DEFAULT_SPEED)) {

    }
}