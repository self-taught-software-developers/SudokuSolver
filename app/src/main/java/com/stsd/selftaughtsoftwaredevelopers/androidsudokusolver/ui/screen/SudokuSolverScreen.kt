package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.rounded.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
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
        floatingActionButton = {
            DefaultFab(
                items = listOf(
                    IconItem(rounded.Undo) { boardState.undoLast() },
                    IconItem(rounded.ClearAll) { boardState.clearBoard() }
                )
            )
        },
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
                cameraEnabled = isCameraOn
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