package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
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
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.sudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.AndroidSudokuSolverTheme

@Composable
fun BoardTile(
    value: String,
    color: Color = Color.Unspecified,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .size(36.dp) //TODO DYNAMIC BOX
            .clickable { onClick() }
            .drawBehind { drawRect(color) },
        contentAlignment = Alignment.Center) {
        Text(text = value)
    }


}

@Composable
fun SudokuBoard(

) {

    var selected by remember { mutableStateOf(Pair(-1,-1)) }
    val board by remember { mutableStateOf(sudokuBoard) }
    
    Surface(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(width = 2.dp, color = Color.Black)
    ) {
        Column(Modifier.width(IntrinsicSize.Max)) {
            board.forEachIndexed { rowIndex , row ->
                if (rowIndex % 3 == 0) {
                    Divider(
                        color = Color.Black,
                        modifier = Modifier
                            .height(2.dp)
                            .fillMaxWidth()
                            .background(Color.Black),
                        thickness = 3.dp
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    row.forEachIndexed { tileIndex, tile ->

                        if (tileIndex % 3 == 0) {
                            Divider(
                                color = Color.Black,
                                modifier = Modifier
                                    .height(36.dp)
                                    .width(2.dp)
                                    .background(Color.Black),
                                thickness = 3.dp
                            )
                        }

                        BoardTile(
                            value = tile.value,
                            color = if (Pair(rowIndex, tileIndex) == selected) {
                                MaterialTheme.colors.primary.copy(alpha = 0.5F)
                            } else {
                                Color.Unspecified
                            }
                        ) { selected = Pair(rowIndex, tileIndex) }
                    }

                }

            }
        }
    }


}

@Preview
@Composable
fun SudokuBoardPreview() {
    AndroidSudokuSolverTheme {
        SudokuBoard()
    }
}

@Preview
@Composable
fun BoardTilePreview() {

    BoardTile(
        value = "1"
    ) {}

}