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
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke.Companion.DefaultMiter
import androidx.compose.ui.graphics.vector.DefaultStrokeLineWidth
import androidx.compose.ui.graphics.vector.VectorProperty
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.Flag
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.padding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Double.min
import java.util.stream.IntStream
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.sqrt

fun DrawScope.drawSudokuLines(
    vector: Int,
    index: Int,
    size: Size,
    color: Color
) {

    val (width, height) = size
    val tileSize = minOf(width, height) / vector
    val point = tileSize * index

    val alpha = if(index % sqrt(vector.toFloat()).toInt() != 0) 0.1f else 1f
    val stroke = DefaultMiter.times(1.5F)

    drawLine(
        alpha = alpha,
        color = color,
        strokeWidth = stroke,
        start = Offset(x = point, y = 0f),
        end = Offset(x = point, y = height),
    )

    drawLine(
        alpha = alpha,
        color = color,
        strokeWidth = stroke,
        start = Offset(x = 0f, y = point),
        end = Offset(x = width, y = point),
    )
}

fun Modifier.drawSudokuGrid(
    color: Color,
    vector: Int
) : Modifier {
    return this@drawSudokuGrid.drawWithCache {
        this@drawWithCache.onDrawBehind {

            repeat(vector) { index ->

                if (index != 0) {
                    drawSudokuLines(
                        vector = vector,
                        size = this@onDrawBehind.size,
                        color = color,
                        index = index,
                    )
                }
            }

        }
    }

}

fun findEmptyPosition(board: Array<Array<Int>>): List<Int> {

    for (row in board) {
        for (column in row) {
            if (column == 0) return listOf(board.indexOf(row), row.indexOf(column))
        }
    }
    return emptyList()
}

fun validatePlacement(
    board: Array<Array<Int>>,
    number: Int,
    position: List<Int>
): Boolean {
    // validate row
    if (board[position[0]].contains(number)) return false

    // validate column
    for (i in board) {
        if (i[position[1]] == number) return false
    }

    val x = position[1] / 3
    val y = position[0] / 3

    for (row in IntStream.range(y * 3, (y * 3) + 3)){
        for (column in IntStream.range(x * 3, (x * 3) + 3)){
            if (board[row][column] == number && listOf(row,column) != position) return false
        }
    }

    return true
}

fun Modifier.drawSudokuGridTiles(
    color: Color,
    vector: Int
) : Modifier {
    return this@drawSudokuGridTiles.drawWithCache {
        this@drawWithCache.onDrawBehind {

            val (width, height) = this@onDrawBehind.size
            val tileWidth = width / vector
            val tileHeight = height / vector

            repeat(vector) { y ->
                repeat(vector) { x ->
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

    val iWidth = with(density) { (maxWidth.toPx()) }
    val iHeight = with(density) { (maxHeight.toPx())}

    return Pair(iWidth, iHeight)
}

fun BoxWithConstraintsScope.calculateLocalPx(density: Density, extra: Dp? = null) : Triple<Float, Float, Float?> {
    with(density) {
        return Triple(maxWidth.toPx(), maxHeight.toPx(), extra?.toPx())
    }
}

@Composable
fun calculatePx() : Pair<Float,Float> {

    val config = LocalConfiguration.current
    val density = LocalDensity.current

    val iWidth = with(density) { (config.screenWidthDp.dp.toPx()) }
    val iHeight = with(density) { (config.screenHeightDp.dp.toPx() )}

    return Pair(iWidth, iHeight)
}

fun Pair<Float, Float>.toAspectRatio() = (second/first).toInt()
fun Pair<Float, Float>.toIntPair() = Pair(this.first.toInt(), this.second.toInt())
fun Triple<Float, Float, *>.toIntPair() = Pair(this.first.toInt(), this.second.toInt())

@Composable
fun BoxWithConstraintsScope.subtractDimensions(extra : Dp) : Pair<Float,Float> {

    val density = LocalDensity.current

    val width = with(density) { (maxWidth - extra).toPx() }
    val height = with(density) { (maxHeight - extra).toPx() }

    return Pair(width, height)
}

fun Triple<Float, Float, Float?>.subtractLocalDimensions() : Pair<Float,Float> {

    return this.third?.let { extra ->
        Pair(first - extra, second - extra)
    } ?: Pair(first, second)

}

@Composable
fun BoxWithConstraintsScope.calculateBoardDimensions() : Rect {

    val (widthPxl, heightPxl) = calculatePx()
    val (width, height) = subtractDimensions((padding.medium * 2))

    val scaling = minOf(width, height)

    val x = (widthPxl - scaling).div(2)
    val y = (heightPxl - scaling).div(2)

    val start = Offset(x = x, y = y)
    val size = Size(scaling,scaling)

    return Rect(offset = start, size = size)

}

fun Int.vector() = this.toDouble().pow(2).toInt()
fun Int.multiplier() = sqrt(this.toDouble()).toInt()

fun BoxWithConstraintsScope.calculateLocalBoardDimensions(
    density: Density,
    padding: Dp
) : Rect {

    val calculatedPx = calculateLocalPx(density, (padding * 2))
    val (sub_width, sub_height) = calculatedPx.subtractLocalDimensions()

    val scaling = minOf(sub_width, sub_height)

    val x = (calculatedPx.first - scaling).div(2)
    val y = (calculatedPx.second - scaling).div(2)

    val start = Offset(x = x, y = y)
    val size = Size(scaling, scaling)

    return Rect(offset = start, size = size)

}



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

    return tiles
}

@Composable
fun ColumnScope.placeTiles(
    modifier: Modifier = Modifier,
    tileColor: Color,
    board: List<Array<TileState>>,
    selectedTile: Triple<Int, Int, Int>?,
    onTileSelected: (Pair<Int, Int>) -> Unit
) = this.apply {

    board.forEachIndexed { x, rowOfTiles ->

        Row {

            rowOfTiles.forEachIndexed { y, tile ->

                BoardTile(
                    modifier = modifier.size(tile.tileSize()),
                    value = tile.value(),
                    color = tile.tileColor(coordinates = selectedTile, color = tileColor)
                ) { onTileSelected(Pair(x, y)) }

            }

        }
    }
}