package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import android.Manifest
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.rounded.ClearAll
import androidx.compose.material.icons.rounded.Undo
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.R.string.BUTTON_request_permission
import androidx.compose.ui.platform.LocalDensity
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.*
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.IconItem
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState.*
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.LocalPadding
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.successGreen
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.DarkPreview

@Composable
fun SudokuSolverScreen(
    modifier: Modifier = Modifier,
    boardState: BoardState,
    updateSolutionSpeed: (TimeState) -> Unit,
) {

    var isCameraOn by rememberSaveable { mutableStateOf(false) }


    val solutionComplete: Boolean by remember(boardState.board) {
        derivedStateOf { boardState.board.all { it.all { it.isValid && it.text.isNotEmpty() } } }
    }
    val moreItems = remember { mutableStateListOf<TimeState>() }

    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier,
        topBar = {
            DefaultTopBar(
                isCameraOn = isCameraOn,
                toggleCamera = { isCameraOn = !isCameraOn },
                placementSpeed = boardState.placementSpeed,
            ) { moreItems.addAll(listOf(INSTANT_SPEED, SUPER_SPEED, DEFAULT_SPEED, SLOW_SPEED)) }
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = { DefaultFab(items = listOf(
            IconItem(icon = rounded.Undo) { boardState.undoLast() },
            IconItem(icon = rounded.ClearAll) { boardState.clearBoard() }
        )) },
        bottomBar = {
             DefaultBottomBar(
                 onClickSolve = { boardState.solveTheBoard() },
                 onEnterValue = { boardState.changeValue(it) }
            )
        },
    ) { bounds ->

        BoxWithConstraints(
            modifier = Modifier
                .padding(bounds)
                .fillMaxSize()
        ) {

            val padding = LocalPadding.current
            val density = LocalDensity.current

            LaunchedEffect(boardState.dimensions) {
                boardState.calculateLocalTileDimensions(
                    constraintsScope = this@BoxWithConstraints,
                    density = density,
                    padding = padding.medium
                )
            }

            SudokuBoard(
                modifier = Modifier.align(Alignment.Center),
                board = boardState.board,
                tiles = boardState.tiles,
                vector = boardState.vector,
                boardColor = if (solutionComplete) successGreen else CustomTheme.colors.primary,
                position = boardState.selectedPosition(),
                cameraEnabled = isCameraOn,
                enterValue = { value, position ->
                    boardState.changeValue(value, position)
                }
            ) { boardState.updateSelectedPositionWith(it) }

            MoreOptionsBar(
                modifier = Modifier.align(Alignment.TopCenter),
                iconList = moreItems
            ) {
                updateSolutionSpeed(it)
                moreItems.clear()
            }

        }

    }

}

@DarkPreview
@Composable
fun SudokuSolverScreenPreview() {
//    SudokuSolverScreen(boardState = TimeState.DEFAULT_SPEED) {
//
//    }
}