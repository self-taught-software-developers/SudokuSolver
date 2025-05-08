package com.stsd.selftaughtsoftwaredevelopers.shared.ui.component

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import com.cerve.development.ui.canvas.component.CerveCanvasWithDrawScope
import com.cerve.development.ui.canvas.model.CerveCanvasColors
import com.cerve.development.ui.canvas.model.CerveSize
import com.cerve.development.ui.canvas.operators.CerveCanvasDefaults
import com.cerve.development.ui.canvas.operators.CerveCanvasDefaults.canvasGridConfigurations
import com.cerve.development.ui.canvas.operators.rememberCanvasGridProperties
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.BoardState
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.PlacementOrigin

@Composable
fun BoxWithConstraintsScope.SudokuBoard(
    state: BoardState,
    modifier: Modifier = Modifier,
    colors: CerveCanvasColors = CerveCanvasDefaults.canvasColors
) {
    val textMeasurer = rememberTextMeasurer()
    val canvasGridProperties = rememberCanvasGridProperties(state.canvasState.gridLineCount)

    val style = TextStyle(
        color = Color.Black
    )

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
            val valueSize = textMeasurer.measure(tile.text, style).size

            val topLeft = tile.centered(
                spacing = canvasGridProperties.spacing,
                valueOffset = Offset(
                    x = valueSize.width.toFloat(),
                    y = valueSize.height.toFloat()
                )
            )

            if (tile.origin == PlacementOrigin.User) {
                drawRect(
                    color = colors.gridCellAltColor,
                    topLeft = tile.topLeft(canvasGridProperties.spacing),
                    size = CerveSize(canvasGridProperties.spacing).toSize
                )
            }

            val adjacent = state.canvasState.selectedCell?.let { cell ->
                val position = cell.position(subgridSize = state.dimensions.multiplier)

                when {
                    position == tile.position -> false
                    position.row == tile.position.row -> true
                    position.column == tile.position.column -> true
                    position.subgrid == tile.position.subgrid -> true
                    else -> false
                }
            } ?: false

            if (adjacent) {
                drawRect(
                    color = colors.gridCellAltColor,
                    topLeft = tile.topLeft(canvasGridProperties.spacing),
                    size = CerveSize(canvasGridProperties.spacing).toSize
                )
            }

            drawText(
                textMeasurer = textMeasurer,
                text = tile.text,
                style = style,
                topLeft = topLeft
            )

        }

    }
}
