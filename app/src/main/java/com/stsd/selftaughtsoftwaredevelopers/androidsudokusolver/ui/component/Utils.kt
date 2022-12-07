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
import androidx.compose.ui.graphics.drawscope.Stroke.Companion.DefaultMiter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.Position
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState
import kotlinx.collections.immutable.PersistentList
import timber.log.Timber
import java.util.stream.IntStream
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

    val alpha = if (index % sqrt(vector.toFloat()).toInt() != 0) 0.1f else 1f
    val stroke = DefaultMiter.times(1.5F)

    drawLine(
        alpha = alpha,
        color = color,
        strokeWidth = stroke,
        start = Offset(x = point, y = 0f),
        end = Offset(x = point, y = height)
    )

    drawLine(
        alpha = alpha,
        color = color,
        strokeWidth = stroke,
        start = Offset(x = 0f, y = point),
        end = Offset(x = width, y = point)
    )
}

fun Modifier.drawSudokuGrid(
    color: Color,
    vector: Int
): Modifier {
    return this@drawSudokuGrid.drawWithCache {
        this@drawWithCache.onDrawBehind {
            repeat(vector) { index ->

                if (index != 0) {
                    drawSudokuLines(
                        vector = vector,
                        size = this@onDrawBehind.size,
                        color = color,
                        index = index
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

fun main() {
    /**
     * x[1] && y[0] valid
     * x[0] && y[1] invalid
     *
     * sx[2] sy[2] valid
     * sx[1] sy[2] invalid
     */


    val board = arrayOf(
        arrayOf(0,1,2,2,3,4,5,6,7),
        arrayOf(1,2,3,4,5,6,7,8,9),
        arrayOf(0,0,2,2,3,4,5,6,7),
        arrayOf(0,1,2,2,3,4,5,6,7),
        arrayOf(0,3,2,2,3,4,5,6,7),
        arrayOf(0,4,2,2,3,4,5,6,7),
        arrayOf(0,5,2,2,3,4,5,6,9),
        arrayOf(0,6,2,2,3,4,1,0,8),
        arrayOf(0,7,2,2,3,4,2,3,4)
    )

    val row = board[1]
    val column = board.map { it[1] }.toTypedArray()

    val position = Position(x = 2, y = 2)

    val subgrid = board.flatMapIndexed { index, ints ->
        ints.filterIndexed { indexInts, _ ->
            position == Position(x = index / 3, y = indexInts / 3 )
        }
    }.toTypedArray()

    val positiono = Position(x = 1, y = 2)

    val subgrido = board.flatMapIndexed { index, ints ->
        ints.filterIndexed { indexInts, _ ->
            positiono == Position(x = index / 3, y = indexInts / 3 )
        }
    }.toTypedArray()

    println(subgrid.isValid(2))
    println(subgrido.isValid(7))
    println(row.isValid(2))
    println(column.isValid(1))

}

fun Any?.logIt(function: String) {
    Timber.d("${Thread.currentThread()} | $function | $this")
}

private fun Array<Int>.isValid(number: Int): Boolean {

    val occurrences = (groupBy { it == number }[true]?.count())

    occurrences.logIt("isValid")

    return (occurrences ?: 0) <= 1
}

fun checkValidity(
    board: Array<Array<Int>>,
    number: Int,
    position: Position
): Boolean {

    val row = board[position.x]
    val column = board.map { it[1] }.toTypedArray()
    val subgrid = board.flatMapIndexed { index, ints ->
        ints.filterIndexed { indexInts, _ ->
            position == Position(x = index / 3, y = indexInts / 3)
        }
    }.toTypedArray()

    return when {
        number == 0 -> true
        (!row.isValid(number)) -> false
        (!column.isValid(number)) -> false
        (!subgrid.isValid(number)) -> false
        else -> true
    }

}

fun validatePlacement(
    board: Array<Array<Int>>,
    number: Int,
    position: List<Int> //todo change to xy
): Boolean {

    if (number == 0) return true
    // validate row
    if (board[position[0]].contains(number)) return false

    // validate column
    for (i in board) {
        if (i[position[1]] == number) return false
    }

    val x = position[1] / 3
    val y = position[0] / 3

    for (row in IntStream.range(y * 3, (y * 3) + 3)) {
        for (column in IntStream.range(x * 3, (x * 3) + 3)) {
            if (board[row][column] == number && listOf(row, column) != position) return false
        }
    }

    return true
}

fun BoxWithConstraintsScope.calculateLocalPx(density: Density, extra: Dp? = null): Triple<Float, Float, Float?> {
    with(density) {
        return Triple(maxWidth.toPx(), maxHeight.toPx(), extra?.toPx())
    }
}

fun Triple<Float, Float, *>.toIntPair() = Pair(this.first.toInt(), this.second.toInt())

fun Triple<Float, Float, Float?>.subtractLocalDimensions(): Pair<Float, Float> {
    return this.third?.let { extra ->
        Pair(first - extra, second - extra)
    } ?: Pair(first, second)
}

fun Int.vector() = this.toDouble().pow(2).toInt()
fun Int.multiplier() = sqrt(this.toDouble()).toInt()
fun Position.div(divisor: Int) = Position(x = x / divisor, y = y / divisor)

fun BoxWithConstraintsScope.calculateLocalBoardDimensions(
    density: Density,
    padding: Dp
): Rect {
    val calculatedPx = calculateLocalPx(density, (padding * 2))
    val (sub_width, sub_height) = calculatedPx.subtractLocalDimensions()

    val scaling = minOf(sub_width, sub_height)

    val x = (calculatedPx.first - scaling).div(2)
    val y = (calculatedPx.second - scaling).div(2)

    val start = Offset(x = x, y = y)
    val size = Size(scaling, scaling)

    return Rect(offset = start, size = size)
}

inline fun <T> List<T>.indexOfFirstOrNull(predicate: (T) -> Boolean): Pair<Int, T>? {
    for ((index, item) in this.withIndex()) {
        if (predicate(item)) {
            return Pair(index, item)
        }
    }
    return null
}

@Composable
fun ColumnScope.placeTiles(
    tileColor: Color,
    tileSize: Dp,
    board: PersistentList<TileState>,
    selectedTile: TileState?,
    modifier: Modifier = Modifier,
    onTileSelected: (Position) -> Unit = { }
) = this.apply {
    board.chunked(9).forEach { row ->

        Row {
            row.forEach { tile ->
                BoardTile(
                    modifier = modifier.size(tileSize),
                    value = tile.value(),
                    color = tile.tileColor(selected = selectedTile, color = tileColor)
                ) { onTileSelected(tile.position) }
            }
        }
    }
}
