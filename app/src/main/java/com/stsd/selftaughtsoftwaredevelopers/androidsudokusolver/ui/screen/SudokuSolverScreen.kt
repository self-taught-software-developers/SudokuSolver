package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.DefaultBottomBar
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.DefaultTopBar
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.SudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.SolutionState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.AllPreviews
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.navigateToEmail
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

    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {

            val context = LocalContext.current
            Row(verticalAlignment = Alignment.CenterVertically){

                DefaultTopBar(
                    modifier = Modifier,
                    placementSpeed = placementSpeed,
                    onSelectionUpdate = { onSolutionSpeedUpdate(it) }
                )

               SudokuBoard(modifier = Modifier.weight(1f),
                board = boardState.board.toPersistentList(),
                selectedPosition = boardState.selectedPosition(),
                color = { color(solutionState) }) { boardState.updateSelectedPosition(it) }

                DefaultBottomBar( modifier = Modifier,
                    color = { color(solutionState) },
                    onUndoLastClick = { onUndoLastClick() },
                    onFeatureRequest = { context.navigateToEmail() },
                    onUndoAllClick = { onUndoAllClick() },
                    onClickSolve = { onSolveBoardClick() },
                    onEnterValue = { onUpdateValueClick(it) })
            }
        }

        else -> {
            Scaffold(backgroundColor = Color.Transparent,
                scaffoldState = scaffoldState,
                modifier = modifier,
                topBar = {
                    DefaultTopBar(placementSpeed = placementSpeed,
                        color = { color(solutionState) },
                        onSelectionUpdate = { onSolutionSpeedUpdate(it) })
                },
                bottomBar = {
                    val context = LocalContext.current

                    DefaultBottomBar(color = { color(solutionState) },
                        onUndoLastClick = { onUndoLastClick() },
                        onFeatureRequest = { context.navigateToEmail() },
                        onUndoAllClick = { onUndoAllClick() },
                        onClickSolve = { onSolveBoardClick() },
                        onEnterValue = { onUpdateValueClick(it) })
                }) { bounds ->

                SudokuBoard(modifier = Modifier
                    .padding(bounds)
                    .fillMaxSize(),
                    board = boardState.board.toPersistentList(),
                    selectedPosition = boardState.selectedPosition(),
                    color = { color(solutionState) }) { boardState.updateSelectedPosition(it) }
            }
        }
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
    SudokuSolverScreen(boardState = BoardState(),
        onSolveBoardClick = { /*TODO*/ },
        onUpdateValueClick = { /*TODO*/ },
        onUndoLastClick = { /*TODO*/ },
        onUndoAllClick = { /*TODO*/ },
        onSolutionSpeedUpdate = { /*TODO*/ })
}
