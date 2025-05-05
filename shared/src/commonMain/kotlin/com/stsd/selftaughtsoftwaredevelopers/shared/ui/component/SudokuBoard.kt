package com.stsd.selftaughtsoftwaredevelopers.shared.ui.component

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.rememberTextMeasurer
import com.cerve.development.ui.canvas.component.CerveCanvasWithDrawScope
import com.cerve.development.ui.canvas.model.CerveCanvasColors
import com.cerve.development.ui.canvas.model.CerveCell
import com.cerve.development.ui.canvas.model.CerveOffset.Companion.offset
import com.cerve.development.ui.canvas.model.CerveSize
import com.cerve.development.ui.canvas.operators.CerveCanvasDefaults
import com.cerve.development.ui.canvas.operators.CerveCanvasDefaults.canvasGridConfigurations
import com.cerve.development.ui.canvas.operators.rememberCanvasGridProperties
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.BoardState

@Composable
fun BoxWithConstraintsScope.SudokuBoard(
    state: BoardState,
    modifier: Modifier = Modifier,
    colors: CerveCanvasColors = CerveCanvasDefaults.canvasColors
) {
    val textMeasurer = rememberTextMeasurer()
    val canvasGridProperties = rememberCanvasGridProperties(state.canvasState.gridLineCount)

    val style = TextStyle(
        color = Color.Black,
//            platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

    val cells = remember(state.canvasState.gridLineCount) {
        mutableListOf<CerveCell>().apply {
            for (row in 0 until state.canvasState.gridLineCount) {
                for (col in 0 until state.canvasState.gridLineCount) {
                    val shift = Offset(row.toFloat(), col.toFloat())
                        .times(canvasGridProperties.spacing.toFloat())
                    val cell = CerveCell(
                        topLeft = shift.offset,
                        cellSize = CerveSize(canvasGridProperties.spacing)
                    )

                    add(cell)
                }
            }
        }
    }


    CerveCanvasWithDrawScope(
        modifier = modifier
            .matchParentSize()
            .clip(RectangleShape),
        canvasState = state.canvasState,
        colors = colors,
        canvasGridConfigurations = canvasGridConfigurations(colors).copy(step = 3),
        canvasGridProperties = canvasGridProperties,
        selectedGridCells = cells
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

            topLeft?.let {
                drawText(
                    textMeasurer = textMeasurer,
                    text = tile.text,
                    style = style,
                    topLeft = topLeft
                )
            }
        }

    }
}
