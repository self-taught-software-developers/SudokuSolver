package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.core.graphics.PathUtils.flatten
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.*
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.EMPTY_TILE
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.toTileText
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.chunked
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.greaterThanOne
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.takeTopOrNull
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.top
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.pow
import kotlin.math.sqrt

data class BoardState(
    val dimensions: GridState = GridState.GRID_3X3,

    val state: BoardActivityState,
    val placementSpeed: TimeState = TimeState.NONE,
    val scope: CoroutineScope,
    val tiles: MutableList<TileState> = mutableListOf(),
    val board: SnapshotStateList<Array<TileState>> = mutableStateListOf(),
    val position: Pair<Int, Int>? = null
) {

    fun isLoading() = state == BoardActivityState.LOADING
    var vector = dimensions.multiplier.toFloat().pow(2).toInt()

    @Composable
    fun calculateTileDimensions(scope : BoxWithConstraintsScope) : BoardState {
        scope.apply {
//            resolution = calculatePx()
            calculateBoardDimensions().apply {

                if (tiles.isEmpty() || sqrt(tiles.size.toFloat()).toInt() != vector) {
                    val (x, y) = this.topLeft
                    val (width, height) = this.size.div(vector.toFloat())

                    (0 until vector).forEach { xp ->
                        (0 until vector).forEach { yp ->
                            tiles.add(
                                TileState(
                                    position = Pair(xp, yp),
                                    rect = Rect(
                                        offset = Offset(
                                            x = (width * xp) + x,
                                            y = (height * yp) + y
                                        ),
                                        size = Size(width, height)
                                    )
                                )
                            )
                        }
                    }

                    board.addAll(toBoardLayout())
                }

            }.also { return this@BoardState }
        }

    }

    fun calculateLocalTileDimensions(
        constraintsScope: BoxWithConstraintsScope,
        padding: Dp,
        density: Density,
    )  {

        println("calculated")
        constraintsScope.apply {
            calculateLocalBoardDimensions(
                density = density,
                padding = padding
            ).let { rect ->

                if (tiles.isEmpty() || sqrt(tiles.size.toFloat()).toInt() != vector) {
                    val (x, y) = rect.topLeft
                    val (width, height) = rect.size.div(vector.toFloat())

                    (0 until vector).forEach { xp ->
                        (0 until vector).forEach { yp ->
                            tiles.add(
                                TileState(
                                    position = Pair(xp, yp),
                                    rect = Rect(
                                        offset = Offset(
                                            x = (width * xp) + x,
                                            y = (height * yp) + y
                                        ),
                                        size = Size(width, height)
                                    )
                                )
                            )
                        }
                    }
                    board.addAll(toBoardLayout())
                }
            }
        }
    }

    private fun toBoardLayout(): List<Array<TileState>> = tiles.chunked(vector)

    private val _selectedPosition = MutableStateFlow<Pair<Int,Int>?>(null)
    var selectedPosition : StateFlow<Pair<Int, Int>?> = _selectedPosition.asStateFlow()

    private var backStack : MutableList<Pair<Int, Int>?> = mutableListOf()

    fun updateSelected(position: Pair<Int, Int>?) = _selectedPosition.update { position }
    fun changeValue(value: String) {

        selectedPosition.value?.let { position ->

            board[position.first] = isPlacementValid(
                value = value,
                position = position,
                board = board.toTypedArray().clone()
            ).clone()

            if (value.isEmpty()) {
                updateSelected(backStack.takeTopOrNull(position))
            } else {
                backStack.top(position)
            }

        }

    }
    private fun changeValue(value: String, position: Pair<Int, Int>) {

        if (value.isEmpty()) {
            updateSelected(backStack.takeTopOrNull(position))
        } else {
            backStack.top(position)
            updateSelected(position)
        }

        board[position.first] = isPlacementValid(
            value = value,
            position = position,
            board = board.toTypedArray().clone()
        ).clone()

    }

    private fun fromUiBoard() : Array<Array<Int>> {
        return board.map { row ->
            row.map { it.value() }.toTypedArray()
        }.toTypedArray()
    }

    fun undoLast() = scope.launch { changeValue(EMPTY_TILE) }
    fun clearBoard() = scope.launch {
        backStack.reversed().onEach { _ ->
            delay(placementSpeed.time).also { undoLast() }
        }
    }

    fun solveTheBoard() = scope.launch { setBoard(findSolutionInstantly(fromUiBoard())) }

    private suspend fun setBoard(board: Array<Array<Int>>) {

        board.forEachIndexed { x, ints ->
            ints.forEachIndexed { y, i ->
                delay(placementSpeed.time).also {
                    changeValue(
                        value = toTileText(i),
                        position = Pair(x,y)
                    )
                }
            }
        }

    }
    private fun findSolutionInstantly(board: Array<Array<Int>>) : Array<Array<Int>> {

        val position = findEmptyPosition(board)

        if (position.isEmpty()) {
            return board
        } else {
            (1..9).forEach { candidate ->
                if (validatePlacement(board, candidate, position)) {
                    board[position[0]][position[1]] = candidate

                    if (findEmptyPosition(findSolutionInstantly(board)).isEmpty()) return board

                    board[position[0]][position[1]] = 0
                }

            }

            return board
        }


    }

    private fun isPlacementValid(
        value: String,
        position: Pair<Int, Int>,
        board: Array<Array<TileState>>
    ) : Array<TileState> {

        position.let { (x,y) ->

            val excluded = mutableSetOf<Pair<Int,Int>>()

            board.apply {
                this[x][y].text = value
                this[x].filter {
                    it.text.isNotEmpty()
                }.valid(excluded)

                this.map { it[y] }.filter {
                    it.text.isNotEmpty()
                }.valid(excluded)

                val area = sqrt(board.size.toDouble()).toInt()

                this.flatMap {
                    it.filter { tile ->
                        tile.position.let { (tx, ty) ->
                            Pair(x/area, y/area) == Pair(tx/area, ty/area)
                        }
                    }
                }.filter { it.text.isNotEmpty() }.valid(excluded)

                return this[x]
            }

        }

    }

    private fun List<TileState>.valid(
        excluded: MutableSet<Pair<Int,Int>>
    ) {

        val occurrenceCount = this.groupingBy { it.text }.eachCount()

        this.filter { !excluded.contains(it.position) }.forEach {
            it.isValid = !occurrenceCount[it.text].greaterThanOne().also { isInvalid ->
                if (isInvalid) {
                    excluded.add(it.position)
                }
            }
        }

    }

}
