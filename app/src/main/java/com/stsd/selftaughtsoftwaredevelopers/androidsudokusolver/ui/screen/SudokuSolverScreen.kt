package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.rounded.ClearAll
import androidx.compose.material.icons.rounded.Undo
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.DefaultBottomBar
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.DefaultTopBar
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.SudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.ThemedFab
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.icon.rounded
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.IconItem
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.AllPreviews
import kotlinx.collections.immutable.persistentListOf
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

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier,
        topBar = {
            DefaultTopBar(
                placementSpeed = placementSpeed,
                dividerColor = boardState.color(),
                onSelectionUpdate = { onSolutionSpeedUpdate(it) }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ThemedFab(
                backgroundColor = boardState.color(),
                items = {
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
                }
            )
        },
        bottomBar = {
            DefaultBottomBar(
                dividerColor = boardState.color(),
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
            boardColor = boardState.color()
        ) { boardState.updateSelectedPositionWith(it) }
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
