package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState.Companion.emptySudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState.Companion.sudokuBoardFilled
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.AndroidSudokuSolverTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.padding

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

@Composable
fun SudokuBoard(
    modifier: Modifier = Modifier,
    board: Array<Array<TileState>>,
    borderColor: Color,
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
                .defaultBorder(borderColor)
                .drawSudokuGrid(borderColor)
        ) {

            PlaceTiles(
                tileSize = tileSize,
                boardOfTiles = board,
                currentlySelectedTile = selectedPosition
            ) { onPositionSelected(Pair(it.first, it.second)) }

        }
    }
}

@Preview
@Composable
fun SudokuBoardPreview() {

    AndroidSudokuSolverTheme {
        val board by remember { mutableStateOf(emptySudokuBoard) }

        SudokuBoard(
            modifier = Modifier,
            board = board,
            borderColor = null.bordColor()
        ) {

        }
    }

}

@Preview
@Composable
fun FillSudokuBoardPreview() {

    AndroidSudokuSolverTheme {
        val board by remember { mutableStateOf(sudokuBoardFilled) }

        SudokuBoard(
            modifier = Modifier,
            board = board,
            borderColor = null.bordColor()
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