package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import com.cerve.development.ui.canvas.component.CerveCanvasWithDrawScope
import com.cerve.development.ui.canvas.model.CerveCanvasState
import com.cerve.development.ui.canvas.operators.CerveCanvasDefaults
import com.cerve.development.ui.canvas.operators.CerveCanvasDefaults.canvasGridConfigurations
import com.cerve.development.ui.canvas.operators.rememberCanvasGridProperties
import com.cerve.development.ui.component.CerveScaffold
import com.cerve.development.ui.component.theme.ExtendedTheme.colors
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.component.SudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.component.SudokuBottomBar
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.component.SudokuTopBar
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.BoardState

@Composable
fun SudokuSolverScreen(
    state: CerveCanvasState,
    modifier: Modifier = Modifier,
    onSolveBoardClick: () -> Unit = { },
    onUpdateValueClick: (String) -> Unit = { },
    onRedoLastClick: () -> Unit = { },
    onUndoLastClick: () -> Unit = { },
    onUndoAllClick: () -> Unit = { },
    onNavigateToSettings: () -> Unit = { }
) {

    CerveScaffold(
        modifier = modifier,
        topBar = {
            SudokuTopBar(
                onNavigateToSettings = onNavigateToSettings
            )
        },
        bottomBar = {
            SudokuBottomBar(
                onRedoLastClick = onRedoLastClick,
                onUndoLastClick = onUndoLastClick,
                onUndoAllClick = onUndoAllClick,
                onSolveClick = onSolveBoardClick,
                onEnterValue = onUpdateValueClick
            )
        }
    ) {
        CerveCanvasWithDrawScope(
            modifier = Modifier
                .matchParentSize()
                .clip(RectangleShape),
            canvasState = state,
            colors = CerveCanvasDefaults.canvasColors,
            canvasGridConfigurations = canvasGridConfigurations(CerveCanvasDefaults.canvasColors).copy(step = 3),
            canvasGridProperties = rememberCanvasGridProperties(state.gridLineCount)
        ) {

        }
//        SudokuBoard(
//            modifier = Modifier.fillMaxSize(),
//            board = boardState.board,
//            color = { colors.primary },
////            selectedPosition = boardState.selectedPosition(),
//        )
    }
}
