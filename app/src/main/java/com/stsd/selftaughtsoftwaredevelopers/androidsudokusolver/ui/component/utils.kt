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
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.GridState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.padding
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun Boolean?.bordColor() : Color {

    return when(this) {
        true -> Color.Green
        false -> CustomTheme.colors.error
        else -> CustomTheme.colors.onSurface
    }

}

fun GridState.vector() : Int = this.multiplier.toFloat().pow(2).toInt()

fun DrawScope.drawSudokuLine(
    vector: Int,
    index: Int,
    start: Offset,
    end: Offset,
    color: Color
) {
    drawLine(
        start = start,
        end = end,
        color = color,
        strokeWidth = Stroke.DefaultMiter,
        alpha = if(index % sqrt(vector.toFloat()).toInt() != 0) 0.1f else 1f,
        blendMode = BlendMode.Exclusion
    )
}

fun Modifier.drawSudokuGrid(
    color: Color,
    vector: Int
) : Modifier {
    return this@drawSudokuGrid.drawWithCache {
        this@drawWithCache.onDrawBehind {
            val (width, height) = this@onDrawBehind.size
            val tileWidth = width / vector

            repeat(vector) { index ->

                if (index != 0) {

                    val x = tileWidth * index

                    drawSudokuLine(
                        vector = vector,
                        index = index,
                        start = Offset(x = x, y = 0f),
                        end = Offset(x = x, y = height),
                        color = color
                    )
                }

            }

            repeat(vector) { index ->

                if (index != 0) {

                    val y = tileWidth * index

                    drawSudokuLine(
                        vector = vector,
                        index = index,
                        start = Offset(x = 0f, y = y),
                        end = Offset(x = width, y = y),
                        color = color
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

            val (width, height) = this@onDrawBehind.size
            val tileWidth = width / dimensions
            val tileHeight = height / dimensions

            repeat(dimensions) { y ->
                repeat(dimensions) { x ->
                    val gridPlacements = Offset(tileWidth * x, tileHeight * y)

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
fun BoxWithConstraintsScope.calculatePx() : Pair<Float,Float> {

    val density = LocalDensity.current

    val iWidth= with(density) { (maxWidth.toPx()) }
    val iHeight = with(density) { (maxHeight.toPx())}

    return Pair(iWidth, iHeight)
}

fun Pair<Float, Float>.toAspectRatio() = (second/first).toInt()
fun Pair<Float, Float>.toInt() = Pair(this.first.toInt(), this.second.toInt())

@Composable
fun BoxWithConstraintsScope.subtractDimensions(extra : Dp) : Pair<Float,Float> {

    val density = LocalDensity.current

    val width = with(density) { (maxWidth - extra).toPx() }
    val height = with(density) { (maxHeight - extra).toPx() }

    return Pair(width, height)
}

@Composable
fun BoxWithConstraintsScope.tileSize() : Dp {

    val density = LocalDensity.current
//    val (iWidth, iHeight) = calculatePx()
//    val (width, height) = subtractDimensions((padding.medium * 2))

    return minOf(maxWidth, maxHeight)

}

@Composable
fun BoxWithConstraintsScope.calculateBoardDimensions() : Rect {

    val (iWidth, iHeight) = calculatePx()
    val (width, height) = subtractDimensions((padding.medium * 2))

    val scalingSize = minOf(width, height)

    val startX = (iWidth - scalingSize) / 2 //this gives us the offset position for our x.
    val startY = (iHeight - scalingSize) / 2  //this gives us the offset position for our y.

    val start = Offset(x = startX, y = startY)

    return Rect(start, Size(scalingSize,scalingSize))

}

@Composable
fun BoxWithConstraintsScope.calculateTileDimensions(cellCount: Int) : ArrayList<TileState> {
    val tiles = arrayListOf<TileState>()

    calculateBoardDimensions().apply {
        val (x, y) = this.topLeft
        val (width, height) = this.size.div(cellCount.toFloat())

        (0 until cellCount).forEach { xp ->
            (0 until cellCount).forEach { yp ->
                tiles.add(
                    TileState(
                        position = Pair(xp, yp),
                        rect = Rect(
                            offset = Offset(x = (width * xp) + x, y = (height * yp) + y),
                            size = Size(width, height)
                        )
                    )

                )
            }
        }
    }

    return tiles
}

fun ArrayList<TileState>.toBoardLayout(vector: Int) = this.chunked(vector)

fun Rect.calculateTileDimensions(cellCount: Int = 9) : ArrayList<TileState> {

    val tiles = arrayListOf<TileState>()
    val (x, y) = this.topLeft
    val (width, height) = this.size.div(cellCount.toFloat())

    (0 until cellCount).forEach { xp ->
        (0 until cellCount).forEach { yp ->
            tiles.add(
                TileState(
                    position = Pair(xp, yp),
                    rect = Rect(
                        offset = Offset(x = (width * xp) + x, y = (height * yp) + y),
                        size = Size(width, height)
                    )
                )

            )
        }
    }

    return tiles.also { it.forEach(::println) ; println(it.size) }
}

@Composable
fun ColumnScope.placeTiles(
    modifier: Modifier = Modifier,
    vector: Int,
    tiles: ArrayList<TileState>,
    selectedTilePosition: Triple<Int, Int, Int>?,
    onTileSelected: (Pair<Int, Int>) -> Unit
) = this.apply {

    tiles.toBoardLayout(vector).forEachIndexed { rowIndex, rowOfTiles ->

        Row {

            rowOfTiles.forEachIndexed { tileIndex, tile ->



                BoardTile(
                    modifier = modifier.size(tile.tileSize()),
                    value = tile.value(),
                    color = tile.tileColor(coordinates = selectedTilePosition)
                ) { onTileSelected(Pair(rowIndex, tileIndex)) }

            }

        }
    }
}