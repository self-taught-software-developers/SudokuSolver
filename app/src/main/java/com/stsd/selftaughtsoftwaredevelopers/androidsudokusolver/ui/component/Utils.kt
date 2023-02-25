package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke.Companion.DefaultMiter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.BuildConfig
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

fun String.logIt() {
    if (BuildConfig.DEBUG) {
        try {
            Timber.d(this)
        } catch (_: Exception) { }
    }
}

fun validatePlacement(
    number: Int,
    position: Position,
    board: Array<Array<Int>>
): Boolean {
    if (number == 0) return true

    // validate row
    if (board[position.x].count { it == number } >= 2) {
        "failed row".logIt()
        return false
    }

    // validate column
    if (board.count { it[position.y] == number } >= 2) {
        "failed column".logIt()
        return false
    }

    val x = position.y / 3
    val y = position.x / 3

    for (row in IntStream.range(y * 3, (y * 3) + 3)) {
        for (column in IntStream.range(x * 3, (x * 3) + 3)) {
            if (board[row][column] == number && Position(row, column) != position) {
                "failed subgrid | ${Position(row, column)}".logIt()
                return false
            }
        }
    }

    return true
}

fun validatePlacement(
    board: Array<Array<Int>>,
    number: Int,
    position: List<Int>
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

fun Int.vector() = this.toDouble().pow(2).toInt()
fun Position.div(divisor: Int) = Position(x = x / divisor, y = y / divisor)

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
