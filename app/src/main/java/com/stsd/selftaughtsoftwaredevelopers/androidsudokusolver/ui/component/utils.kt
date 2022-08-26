package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.LocalPadding

@Composable
fun Boolean?.bordColor() : Color {

    return when(this) {
        true -> Color.Green
        false -> CustomTheme.colors.error
        else -> CustomTheme.colors.onSurface
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

fun Modifier.drawSudokuGridTiles(
    color: Color,
    dimensions: Int = 9
) : Modifier {
    return this@drawSudokuGridTiles.drawWithCache {
        this@drawWithCache.onDrawBehind {
            println("canvas $size")
            val (width, height) = this@onDrawBehind.size
            val tileWidth = width / dimensions
            val tileHeight = height / dimensions

            repeat(dimensions) { y ->
                repeat(dimensions) { x ->
                    val gridPlacements = Offset(tileWidth * x, tileHeight * y)

//                    println(gridPlacements)
center
                    drawRect(
                        topLeft = gridPlacements,
                        color = color,
                        style = Stroke(width = 20f),
                        size = Size(tileWidth, tileWidth)
                    )
                }
            }
        }
    }

}

@Composable
fun BoxWithConstraintsScope.calculateBoardDimensions() : Rect {

    val density = LocalDensity.current
    val padding = LocalPadding.current

    val iWidth= with(density) { (maxWidth.toPx()) }
    val iHeight = with(density) { (maxHeight.toPx())}

    val width = with(density) { (maxWidth - (padding.medium * 2)).toPx() }
    val height = with(density) { (maxHeight - (padding.medium * 2)).toPx() }

    val scalingSize = minOf(width, height)

    val startX = (iWidth - scalingSize)/2 //this gives us the offset position for our x.
    val startY = (iHeight - scalingSize)/2  //this gives us the offset position for our y.

    val start = Offset(x = startX, y = startY)

    return Rect(start, Size(scalingSize,scalingSize))

}

fun Rect.calculateTileDimensions(cellCount: Int = 3) : ArrayList<Rect> {

    val tiles = arrayListOf<Rect>()
    val (x, y) = this.topLeft
    val (width, height) = this.size.div(cellCount.toFloat())

    (0 until cellCount).forEach { xp ->
        (0 until cellCount).forEach { yp ->
            tiles.add(
                Rect(
                    offset = Offset(
                        x = (width * xp) + x,
                        y = (height * yp) + y
                    ),
                    size = Size(width, height)
                )
            )
        }
    }

    return tiles.also { it.forEach(::println) ; println(it.size) }
}

@Composable
fun ColumnScope.PlaceTiles(
    tileSize: Dp,
    boardOfTiles: Array<Array<TileState>>,
    selectedTilePosition: Triple<Int, Int, Int>?,
    onTileSelected: (Pair<Int, Int>) -> Unit
) {
    this.apply {
        boardOfTiles.forEachIndexed { rowIndex, rowOfTiles ->

            Row {

                rowOfTiles.forEachIndexed { tileIndex, tile ->

                    BoardTile(
                        modifier = Modifier.size(tileSize),
                        value = tile.value(),
                        color = tile.tileColor(coordinates = selectedTilePosition)
                    ) { onTileSelected(Pair(rowIndex, tileIndex)) }

                }

            }
        }
    }
}