package com.stsd.selftaughtsoftwaredevelopers.shared.ui.component

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import com.cerve.development.ui.canvas.model.CerveCanvasGridProperties
import com.cerve.development.ui.canvas.model.CerveSize
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.BoardState
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.PlacementOrigin
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.TileState

fun DrawScope.drawSolverAdjacent(
    state: BoardState,
    tile: TileState,
    color: Color,
    canvasGridProperties: CerveCanvasGridProperties
) {
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
        when(tile.origin) {
            PlacementOrigin.UserError -> {
                drawRect(
                    color = Color.Red,
                    topLeft = tile.topLeft(canvasGridProperties.spacing),
                    size = CerveSize(canvasGridProperties.spacing).toSize
                )
            }
            else -> {
                drawRect(
                    color = color,
                    topLeft = tile.topLeft(canvasGridProperties.spacing),
                    size = CerveSize(canvasGridProperties.spacing).toSize
                )
            }
        }
    }
}

fun DrawScope.drawSolverSelected(
    tile: TileState,
    color: Color,
    canvasGridProperties: CerveCanvasGridProperties
) {
    when(tile.origin) {
        PlacementOrigin.User -> {
            drawRect(
                color = color,
                topLeft = tile.topLeft(canvasGridProperties.spacing),
                size = CerveSize(canvasGridProperties.spacing).toSize
            )
        }
        PlacementOrigin.UserError -> {
            drawRect(
                color = Color.Red,
                topLeft = tile.topLeft(canvasGridProperties.spacing),
                size = CerveSize(canvasGridProperties.spacing).toSize
            )
        }
        else -> Unit
    }
}

fun DrawScope.drawSolverText(
    tile: TileState,
    style: TextStyle,
    textMeasurer: TextMeasurer,
    canvasGridProperties: CerveCanvasGridProperties
) {

    val valueSize = textMeasurer.measure(tile.text, style).size

    val topLeft = tile.centered(
        spacing = canvasGridProperties.spacing,
        valueOffset = Offset(
            x = valueSize.width.toFloat(),
            y = valueSize.height.toFloat()
        )
    )

    drawText(
        textMeasurer = textMeasurer,
        text = tile.text,
        style = style,
        topLeft = topLeft
    )
}