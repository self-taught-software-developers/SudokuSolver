package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.PlatformParagraphStyle
import androidx.compose.ui.text.PlatformSpanStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.sp
import com.cerve.development.ui.canvas.component.CerveCanvasWithDrawScope
import com.cerve.development.ui.canvas.model.CerveCanvasState
import com.cerve.development.ui.canvas.model.CerveOffset
import com.cerve.development.ui.canvas.operators.CerveCanvasDefaults
import com.cerve.development.ui.canvas.operators.CerveCanvasDefaults.canvasGridConfigurations
import com.cerve.development.ui.canvas.operators.rememberCanvasGridProperties
import com.cerve.development.ui.component.CerveScaffold
import com.cerve.development.ui.component.theme.ExtendedTheme.colors
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.component.SudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.component.SudokuBottomBar
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.component.SudokuTopBar
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.BoardState
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.TileState

@Composable
fun SudokuSolverScreen(
    state: BoardState,
    modifier: Modifier = Modifier,
    onSolveBoardClick: () -> Unit = { },
    onUpdateValueClick: (Int) -> Unit = { },
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
        SudokuBoard(
            state = state
        )
    }
}
