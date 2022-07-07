package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.EMPTY_TILE
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.sudokuBoardArray
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.AndroidSudokuSolverTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme

@Composable
fun BoardTile(
    value: String,
    color: Color = Color.Unspecified,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .size(CustomTheme.sizing.x_large)
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
    selectedPosition: Pair<Int, Int>? = null,
    onPositionSelected: (Pair<Int, Int>) -> Unit
) {

    val borderColor by remember(board) {
        derivedStateOf { (board.filledBoard()) }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Surface(
            shape = RoundedCornerShape(CustomTheme.sizing.small),
            border = BorderStroke(
                width = CustomTheme.sizing.x_small,
                color = borderColor.borderColor()
            )
        ) {

            Column(modifier = Modifier.width(IntrinsicSize.Max)) {

                board.forEachIndexed { rowIndex, row ->

                    (rowIndex % 3 == 0).HorizontalDivider(borderColor.borderColor())

                    Row(modifier = Modifier.height(IntrinsicSize.Max)) {

                        row.forEachIndexed { tileIndex, tile ->

                            (tileIndex % 3 == 0).VerticalDivider(borderColor.borderColor())

                            BoardTile(
                                value = tile.text,
                                color = (Pair(rowIndex, tileIndex) == selectedPosition)
                                    .tileColor()
                            ) {
                                onPositionSelected(Pair(rowIndex, tileIndex))
                            }
                        }
                    }
                }
            }

        }

    }
}

fun Array<Array<TileState>>.filledBoard() : Boolean =
    flatten().firstOrNull { it.text == EMPTY_TILE } == null

@Composable
fun Boolean.borderColor() : Color {
    return if(this) {
        CustomTheme.colors.primary.copy(alpha = 0.5F)
    } else {
        Color.Black
    }
}
@Composable
fun Boolean?.tileColor() : Color {
    return if (this == true) {
        CustomTheme.colors.primary.copy(alpha = 0.15F)
    } else {
        Color.Unspecified
    }
}

@Composable
fun Boolean.HorizontalDivider(color: Color) {
    if (this) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(CustomTheme.sizing.x_small)
                .background(color)
        )
    } else {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(CustomTheme.sizing.x_small)
                .background(color.copy(alpha = 0.001F)),
        )
    }

}

@Composable
fun Boolean.VerticalDivider(color: Color) {
    if (this) {
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(CustomTheme.sizing.x_small)
                .background(color),
        )
    } else {
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(CustomTheme.sizing.x_small)
                .background(color.copy(alpha = 0.001F)),
        )
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
fun BoardTilePreview() {

    AndroidSudokuSolverTheme {
        BoardTile(value = "1") {

        }
    }

}