package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.Tile
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.sudokuBoard
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
            .size(CustomTheme.sizing.massive) //TODO DYNAMIC BOX
            .clickable { onClick() }
            .drawBehind { drawRect(color) },
        contentAlignment = Alignment.Center) {
        Text(text = value)
    }


}

/**
 * This sudoku board is completely clickable.
 * when a user clicks on any of the @[Tile] they'll have the ability to change the value within it.
 */
@Composable
fun SudokuBoard(board: List<List<Tile>>) {

    var selected by remember { mutableStateOf(Pair(-1,-1)) }
    
    Surface(
        shape = RoundedCornerShape(CustomTheme.sizing.small),
        border = BorderStroke(
            width = CustomTheme.sizing.xTiny,
            color = Color.Black
        )
    ) {
        Column(modifier = Modifier.width(IntrinsicSize.Max)) {

            board.forEachIndexed { rowIndex , row ->

                (rowIndex % 3 == 0).HorizontalDivider()

                Row(
                    modifier = Modifier.height(IntrinsicSize.Max),
                    horizontalArrangement = Arrangement.Center
                ) {

                    row.forEachIndexed { tileIndex, tile ->

                        (tileIndex % 3 == 0).VerticalDivider()

                        BoardTile(
                            value = tile.value,
                            color = (Pair(rowIndex, tileIndex) == selected).tileColor()
                        ) { selected = Pair(rowIndex, tileIndex) }
                    }

                }

            }
        }
    }
}

@Composable
fun Boolean.tileColor() : Color {
    return if (this) {
        CustomTheme.colors.primary.copy(alpha = 0.15F)
    } else {
        Color.Unspecified
    }
}

@Composable
fun Boolean.HorizontalDivider() {
    if (this) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(CustomTheme.sizing.xTiny)
                .background(Color.Black)
        )
    } else {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(CustomTheme.sizing.xTiny)
                .background(Color.Black.copy(alpha = 0.001F)),
        )
    }

}

@Composable
fun Boolean.VerticalDivider() {
    if (this) {
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(CustomTheme.sizing.xTiny)
                .background(Color.Black),
        )
    } else {
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(CustomTheme.sizing.xTiny)
                .background(Color.Black.copy(alpha = 0.001F)),
        )
    }
}

@Preview
@Composable
fun SudokuBoardPreview() {
    AndroidSudokuSolverTheme {
        val board by remember { mutableStateOf(sudokuBoard) }

        SudokuBoard(board)
    }
}

@Preview
@Composable
fun BoardTilePreview() {

    BoardTile(
        value = "1"
    ) {}

}