package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme

@Composable
fun Array<Array<TileState>>.filledBoard() : Color {

    return if(flatten().firstOrNull { it.text == TileState.EMPTY_TILE } == null) {
        Color.Green
    } else {
        CustomTheme.colors.primary
    }

}

fun Modifier.drawSudokuGrid(color: Color) : Modifier {
    return this@drawSudokuGrid.drawWithCache {
        this@drawWithCache.onDrawBehind {
            val (width, height) = this@onDrawBehind.size
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
    }

}

@Composable
fun ColumnScope.PlaceTiles(
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
fun Boolean?.tileColor() : Color {
    return if (this == true) {
        CustomTheme.colors.primary.copy(alpha = 0.15F)
    } else {
        Color.Unspecified
    }
}
