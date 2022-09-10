package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.rounded.ClearAll
import androidx.compose.material.icons.rounded.Undo
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.*
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.*
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


    var expanded by remember { mutableStateOf(false) }
    val moreTimeStatItems = remember { mutableStateListOf<TimeState>() + TimeState.values() }

    val solutionComplete: Boolean by remember(boardState.board) {
        derivedStateOf { boardState.board.all { it.all { it.isValid && it.text.isNotEmpty() } } }
    }

    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier,
        topBar = {
            MoreOptionsBar(
                items = listOf(boardState.placementSpeed, GridState.GRID_3X3)
            ) {
                updateSolutionSpeed(it)
            }
//            DefaultTopBar(
//                placementSpeed = moreSpeedItems.first(),
//            ) {  }
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
                position = boardState.selectedPosition(),
                vector = boardState.vector,
                boardColor = if (solutionComplete) successGreen else CustomTheme.colors.primary
            ) { boardState.updateSelectedPositionWith(it) }

//            MoreOptionsBar(
//                modifier = Modifier.align(Alignment.TopCenter),
//                iconList = moreSpeedItems
//            ) {
//                updateSolutionSpeed(it)
//            }

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