package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.calculateLocalBoardDimensions
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.findEmptyPosition
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.validatePlacement
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.vector
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.EMPTY_TILE
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.toTileText
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.chunked
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.greaterThanOne
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.sqrt

data class BoardState(
    val dimensions: GridState = GridState.GRID_3X3,
    val state: BoardActivityState = BoardActivityState.LOADING
) {
    val vector = dimensions.multiplier.vector()

    val board: SnapshotStateList<List<TileState>> = mutableStateListOf()
    private val backStack : SnapshotStateList<Pair<Int, Int>?> = mutableStateListOf()

    private val _placementSpeed = MutableStateFlow(TimeState.DEFAULT_SPEED)
    var placementSpeed: StateFlow<TimeState> = _placementSpeed.asStateFlow()

    fun updatePlacementSpeed(value: TimeState) = _placementSpeed.update { value }
    
    fun selectedPosition() = backStack.lastOrNull()
    fun isLoading() = state == BoardActivityState.LOADING

    fun calculateLocalTileDimensions(
        constraintsScope: BoxWithConstraintsScope,
        padding: Dp,
        density: Density,
    ) = constraintsScope.calculateLocalBoardDimensions(
        density = density,
        padding = padding
    ).also { rect ->
        val (x, y) = rect.topLeft
        val (width, height) = rect.size.div(vector.toFloat())

        List(vector) { xp ->
            List(vector) { yp ->
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
            }
        }.also { board.addAll(it) }

//        (0 until vector).map  { xp ->
//            (0 until vector).map { yp ->
//                TileState(
//                    position = Pair(xp, yp),
//                    rect = Rect(
//                        offset = Offset(
//                            x = (width * xp) + x,
//                            y = (height * yp) + y
//                        ),
//                        size = Size(width, height)
//                    )
//                )
//            }
//        }.mapTo(board)
    }


    fun updateSelectedPositionWith(position: Pair<Int, Int>?) {
        position?.let {
            backStack.add(position)
        } ?: backStack.removeLastOrNull()
    }

    fun changeValue(value: String) = selectedPosition()?.let { position ->

        board[position.first] = isPlacementValid(
            value = value,
            position = position,
            board = board
        )

        if (value.isEmpty()) {
            updateSelectedPositionWith(null)
        }

    }
    private fun changeValue(value: String, position: Pair<Int, Int>) {

//        if (value.isEmpty()) {
//            updateSelectedPositionWith(null)
//        } else {
//            updateSelectedPositionWith(position)
//        }
//
//        board[position.first] = isPlacementValid(
//            value = value,
//            position = position,
//            board = board.toTypedArray().clone()
//        ).copyOf()

    }

    private fun fromUiBoard() : Array<Array<Int>> {
        return board.map { row ->
            row.map { it.value() }.toTypedArray()
        }.toTypedArray()
    }

    fun undoLast() { changeValue(EMPTY_TILE) }
    suspend fun clearBoard() {
        while (selectedPosition() != null) {
            delay(placementSpeed.value.time).also { undoLast() }
        }
    }

    suspend fun solveTheBoard() {
        if (solvable()) { setBoard(findSolutionInstantly(fromUiBoard())) }
    }

    private suspend fun setBoard(solvedBoard: Array<Array<Int>>) {

        fromUiBoard().forEachIndexed { x, ints ->
            ints.forEachIndexed { y, i ->
                if (i == 0) {
                    delay(placementSpeed.value.time).also {
                        changeValue(
                            value = toTileText(solvedBoard[x][y]),
                            position = Pair(x,y)
                        )
                    }
                }
            }
        }

    }
    private fun findSolutionInstantly(board: Array<Array<Int>>) : Array<Array<Int>> {

        findEmptyPosition(board).also { position ->
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

    }

    private fun isPlacementValid(
        value: String,
        position: Pair<Int, Int>,
        board: List<List<TileState>>
    ) : List<TileState> {

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

    fun allTilesAreValid() : Boolean {
        return board.all { row -> row.all { tile -> tile.isValid && tile.text.isNotEmpty() } }
    }
    private fun solvable() : Boolean {
        return board.all { row ->
            row.all { tile -> tile.isValid } && row.any { tile -> tile.text.isEmpty() }
        }
    }
}
