package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.VectorProperty
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.EMPTY_TILE
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.sudokuBoardArray
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.sudokuBoardFilledArray
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.AndroidSudokuSolverTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.padding
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.sizing
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.LocalPadding
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.Wine500
import kotlin.math.floor

@Composable
fun BoardTile(
    modifier: Modifier,
    value: String,
    color: Color = Color.Unspecified,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .clickable { onClick() }
            .drawBehind { drawRect(color) },
        contentAlignment = Alignment.Center
    ) {
        Text(text = value)
    }

}

/**
 * This sudoku board is completely clickable.
 * when a user clicks on any of the @[TileState] they'll have the ability to change the value within it.
 */
@Composable
fun SudokuBoard(
    modifier: Modifier = Modifier,
    board: Array<Array<TileState>>,
    borderColor: Color = board.filledBoard(),
    selectedPosition: Pair<Int, Int>? = null,
    onPositionSelected: (Pair<Int, Int>) -> Unit
) {

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = padding.medium),
        contentAlignment = Alignment.Center
    ) {

        val tileSize =  (minOf(maxWidth, maxHeight) / 9)

        Column(
            modifier = Modifier
                .border(
                    border = BorderStroke(
                        width = sizing.xx_small,
                        color = borderColor
                    ),
                    shape = CustomTheme.shapes.medium
                )
                .drawWithCache {
                    this.onDrawBehind {
                        drawGrid(borderColor)
                    }
                }
        ) {

            PlaceTiles(
                tileSize = tileSize,
                boardOfTiles = board,
                currentlySelectedTile = selectedPosition
            ) { onPositionSelected(Pair(it.first, it.second)) }

        }
    }

}

@Composable
fun Array<Array<TileState>>.filledBoard() : Color {

    return if(flatten().firstOrNull { it.text == EMPTY_TILE } == null) {
        Color.Green
    } else {
        CustomTheme.colors.primary
    }

}

private fun DrawScope.drawGrid(color: Color) {
    val (width, height) = size
    val tileWidth = width / 9

    repeat(9) { index ->

        val x = tileWidth * index

        if (index != 0) {
            drawLine(
                start = Offset(x = x, y = 0f),
                end = Offset(x = x, y = height),
                color = color,
                strokeWidth = Stroke.DefaultMiter,
                alpha = if(index % 3 != 0) 0.1f else 1f
            )
        }

    }

    repeat(9) { index ->

        val y = tileWidth * index

        if (index != 0) {
            drawLine(
                start = Offset(x = 0f, y = y),
                end = Offset(x = width, y = y),
                color = color,
                strokeWidth = Stroke.DefaultMiter,
                alpha = if(index % 3 != 0) 0.1f else 1f
            )
        }

    }
}

@Composable
private fun ColumnScope.PlaceTiles(
    tileSize: Dp,
    boardOfTiles: Array<Array<TileState>>,
    currentlySelectedTile: Pair<Int, Int>?,
    onTileSelected: (Pair<Int, Int>) -> Unit
) {
    this.apply {
        boardOfTiles.forEachIndexed { rowIndex, rowOfTiles ->

            Row {

                rowOfTiles.forEachIndexed { tileIndex, tile ->

                    BoardTile(
                        modifier = Modifier.size(tileSize),
                        value = tile.text,
                        color = (Pair(rowIndex, tileIndex) == currentlySelectedTile)
                            .tileColor()
                    ) { onTileSelected(Pair(rowIndex, tileIndex)) }

                }

            }
        }
    }

}

@Composable
private fun Boolean?.tileColor() : Color {
    return if (this == true) {
        CustomTheme.colors.primary.copy(alpha = 0.15F)
    } else {
        Color.Unspecified
    }
}

@Preview
@Composable
fun SudokuBoardPreview() {

    AndroidSudokuSolverTheme {
        val board by remember { mutableStateOf(sudokuBoardArray) }

        SudokuBoard(
            modifier = Modifier,
            board = board
        ) {

        }
    }

}

@Preview
@Composable
fun FillSudokuBoardPreview() {

    AndroidSudokuSolverTheme {
        val board by remember { mutableStateOf(sudokuBoardFilledArray) }

        SudokuBoard(
            modifier = Modifier,
            board = board
        ) {

        }
    }
}

@Preview
@Composable
fun BoardTilePreview() {

    AndroidSudokuSolverTheme {
        BoardTile(
            modifier = Modifier,
            value = "1"
        ) {

        }
    }

}