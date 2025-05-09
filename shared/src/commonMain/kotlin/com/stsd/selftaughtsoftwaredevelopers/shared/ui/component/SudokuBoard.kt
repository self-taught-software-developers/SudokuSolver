package com.stsd.selftaughtsoftwaredevelopers.shared.ui.component

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import com.cerve.development.ui.canvas.component.CerveCanvasWithDrawScope
import com.cerve.development.ui.canvas.model.CerveCanvasColors
import com.cerve.development.ui.canvas.operators.CerveCanvasDefaults
import com.cerve.development.ui.canvas.operators.CerveCanvasDefaults.canvasGridConfigurations
import com.cerve.development.ui.canvas.operators.rememberCanvasGridProperties
import com.cerve.development.ui.component.theme.ExtendedTheme
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.BoardState

@Composable
fun BoxWithConstraintsScope.SudokuBoard(
    state: BoardState,
    modifier: Modifier = Modifier,
    colors: CerveCanvasColors = CerveCanvasDefaults.canvasColors
) {
    val textMeasurer = rememberTextMeasurer()
    val canvasGridProperties = rememberCanvasGridProperties(state.canvasState.gridLineCount)

    val style = TextStyle(color = ExtendedTheme.colors.onSurface)

    CerveCanvasWithDrawScope(
        modifier = modifier
            .matchParentSize()
            .clip(RectangleShape),
        canvasState = state.canvasState,
        colors = colors,
        canvasGridConfigurations = canvasGridConfigurations(colors = colors, step = 3),
        canvasGridProperties = canvasGridProperties
    ) {

        state.board.forEach { tile ->

            drawSolverAdjacent(
                state = state,
                tile = tile,
                color = colors.gridCellAltColor.copy(0.5f),
                canvasGridProperties = canvasGridProperties
            )

            drawSolverSelected(
                tile = tile,
                color = colors.gridCellAltColor.copy(0.5f),
                canvasGridProperties = canvasGridProperties
            )

            drawSolverText(
                tile = tile,
                style = style,
                textMeasurer = textMeasurer,
                canvasGridProperties = canvasGridProperties
            )

        }

    }
}
