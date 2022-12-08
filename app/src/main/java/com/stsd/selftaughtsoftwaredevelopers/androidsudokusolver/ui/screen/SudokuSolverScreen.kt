package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.DefaultBottomBar
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.DefaultTopBar
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.SudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.SolutionState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.AllPreviews
import kotlinx.collections.immutable.toPersistentList

@Composable
fun SudokuSolverScreen(
    boardState: BoardState,
    onSolveBoardClick: () -> Unit,
    onUpdateValueClick: (String) -> Unit,
    onUndoLastClick: () -> Unit,
    onUndoAllClick: () -> Unit,
    onSolutionSpeedUpdate: (TimeState) -> Unit,
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val placementSpeed by boardState.placementSpeed.collectAsState()
    val solutionState by boardState.solutionState.collectAsState()

    Scaffold(
        backgroundColor = Color.Transparent,
        scaffoldState = scaffoldState,
        modifier = modifier,
        topBar = {
            DefaultTopBar(
                placementSpeed = placementSpeed,
                color = { color(solutionState) },
                onSelectionUpdate = { onSolutionSpeedUpdate(it) }
            )
        },
        bottomBar = {
            DefaultBottomBar(
                color = { color(solutionState) },
                onUndoLastClick = { onUndoLastClick() },
                onFeatureRequest = { },
                onUndoAllClick = { onUndoAllClick() },
                onClickSolve = { onSolveBoardClick() },
                onEnterValue = { onUpdateValueClick(it) }
            )
        }
    ) { bounds ->

        SudokuBoard(
            modifier = Modifier
                .padding(bounds)
                .fillMaxSize(),
            board = boardState.board.toPersistentList(),
            selectedPosition = boardState.selectedPosition(),
            color = { color(solutionState) }
        ) { boardState.updateSelectedPosition(it) }
    }
}

@Composable
fun color(state: SolutionState): Color {
    return when (state) {
        SolutionState.SUCCESS -> Color.Green
        SolutionState.ERROR -> Color.Red
        else -> colors.primary
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
