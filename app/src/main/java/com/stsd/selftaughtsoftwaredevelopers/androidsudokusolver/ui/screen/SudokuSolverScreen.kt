package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.rounded.ClearAll
import androidx.compose.material.icons.rounded.Undo
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.cerve.co.material3extension.designsystem.ExtendedTheme.spacing
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.DefaultBottomBar
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.DefaultTopBar
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.SudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.ThemedFab
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.icon.rounded
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.IconItem
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.successGreen500
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.AllPreviews
import kotlinx.collections.immutable.persistentListOf

@Composable
fun SudokuSolverScreen(
    boardState: BoardState,
    onSolveBoardClick: () -> Unit,
    onUpdateValueClick: (String) -> Unit,
    onUndoLastClick: () -> Unit,
    onUndoAllClick: () -> Unit,
    onSolutionSpeedUpdate: (TimeState) -> Unit,
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
) {

    val solutionComplete by remember(boardState.board) {
        derivedStateOf { boardState.allTilesAreValid() }
    }

    val placementSpeed by boardState.placementSpeed.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier,
        topBar = {
            DefaultTopBar(
                placementSpeed = placementSpeed,
                onSelectionUpdate = { onSolutionSpeedUpdate(it) }
            )
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

            val density = LocalDensity.current
            val padding = spacing.default

            LaunchedEffect(boardState.dimensions) {
                boardState.calculateLocalTileDimensions(
                    constraintsScope = this@BoxWithConstraints,
                    density = density,
                    padding = padding
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

    SudokuSolverScreen(
        boardState = BoardState(),
        onSolveBoardClick = { /*TODO*/ },
        onUpdateValueClick = { /*TODO*/ },
        onUndoLastClick = { /*TODO*/ },
        onUndoAllClick = { /*TODO*/ },
        onSolutionSpeedUpdate = { /*TODO*/ }
    )

}