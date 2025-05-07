package com.stsd.selftaughtsoftwaredevelopers.shared.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cerve.development.ui.component.CerveScaffold
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.component.SudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.component.SudokuBottomBar
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.component.SudokuTopBar
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.BoardState

@Composable
fun SudokuSolverScreen(
    state: BoardState,
    modifier: Modifier = Modifier,
    onSolveBoardClick: () -> Unit = { },
    onUpdateValueClick: (Int) -> Unit = { },
    onDeleteClick: () -> Unit = { },
    onResetClick: () -> Unit = { },
) {

    CerveScaffold(
        modifier = modifier,
        bottomBar = {
            SudokuBottomBar(
                onResetClick = onResetClick,
                onDeleteClick = onDeleteClick,
                onSolveClick = onSolveBoardClick,
                onEnterValue = onUpdateValueClick
            )
        }
    ) { SudokuBoard(state = state) }
}
