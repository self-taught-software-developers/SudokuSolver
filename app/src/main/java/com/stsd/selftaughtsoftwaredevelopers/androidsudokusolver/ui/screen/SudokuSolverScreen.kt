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
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.DefaultBottomBar
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.DefaultTopBar
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.SudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.ThemedFab
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.icon.rounded
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.IconItem
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.AndroidSudokuSolverTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.ExtendedTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.ExtendedTheme.colors
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.LocalPadding
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.successGreen500
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.AllPreviews
import kotlinx.collections.immutable.persistentListOf

@Composable
fun SudokuSolverScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    boardState: BoardState,
    onSolveBoardClick: () -> Unit,
    onUpdateValueClick: (String) -> Unit,
    onUndoLastClick: () -> Unit,
    onUndoAllClick: () -> Unit,
    updateSolutionSpeed: (TimeState) -> Unit,
) {

    val solutionComplete by remember(boardState.board) {
        derivedStateOf { boardState.allTilesAreValid() }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier,
        topBar = {
            DefaultTopBar(
                placementSpeed = boardState.placementSpeed
            ) { updateSolutionSpeed(it) }
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ThemedFab(items = {
                persistentListOf(
                    IconItem(
                        icon = rounded.Undo,
                        onClick = onUndoLastClick
                    ),
                    IconItem(
                        icon = rounded.ClearAll,
                        onClick = onUndoAllClick
                    )
                )
            })
        },
        bottomBar = {
             DefaultBottomBar(
                 onClickSolve = { onSolveBoardClick() },
                 onEnterValue = { onUpdateValueClick(it) }
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

            //read is happening when call get bad state
            SudokuBoard(
                modifier = Modifier.align(Alignment.Center),
                board = boardState.board,
                position = boardState.selectedPosition(),
                vector = boardState.vector,
                boardColor = if (solutionComplete) successGreen500 else colors.primary
            ) { boardState.updateSelectedPositionWith(it) }

        }

    }

}

@AllPreviews
@Composable
fun SudokuSolverScreenPreview() {

    AndroidSudokuSolverTheme {

        SudokuSolverScreen(
            boardState = BoardState(),
            onSolveBoardClick = { /*TODO*/ },
            onUpdateValueClick = { /*TODO*/ },
            onUndoLastClick = { /*TODO*/ },
            onUndoAllClick = { /*TODO*/ },
            updateSolutionSpeed = { /*TODO*/ }
        )

    }

}