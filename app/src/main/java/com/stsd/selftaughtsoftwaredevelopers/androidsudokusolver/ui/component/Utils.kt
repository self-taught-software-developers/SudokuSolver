package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState

@Composable
fun Boolean?.bordColor(): Color {
    return when (this) {
        true -> Color.Green
        false -> ExtendedTheme.colors.error
        else -> ExtendedTheme.colors.onSurface
    }
}

fun Modifier.drawSudokuGrid(color: Color): Modifier {
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
                        alpha = if (index % 3 != 0) 0.1f else 1f
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
                        alpha = if (index % 3 != 0) 0.1f else 1f
                    )
                }
            }
        }
    }
}

@Composable
fun ColumnScope.placeTiles(
    tileSize: Dp,
    sudokuRow: Pair<Int, Array<TileState>>,
    selectedTilePosition: Triple<Int, Int, Int>?,
    modifier: Modifier = Modifier,
    onTileSelected: (Pair<Int, Int>) -> Unit = { }
) = this.apply {
    Row(modifier = modifier) {
        sudokuRow.second.forEachIndexed { tileIndex, tile ->
            BoardTile(
                modifier = Modifier.size(tileSize),
                value = tile.value(),
                color = tile.tileColor(coordinates = selectedTilePosition)
            ) { onTileSelected(Pair(sudokuRow.first, tileIndex)) }
        }
    }
}
