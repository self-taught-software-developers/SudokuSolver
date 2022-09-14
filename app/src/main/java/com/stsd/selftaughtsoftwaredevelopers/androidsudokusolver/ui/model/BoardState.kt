package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.StateRecord
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.*
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.EMPTY_TILE
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.greaterThanOne
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.annotation.Untainted
import kotlin.math.sqrt

data class BoardState(
    val dimensions: GridState = GridState.GRID_3X3,
    val state: BoardActivityState = BoardActivityState.LOADING
) {
    val vector = dimensions.multiplier.vector()

    val board: SnapshotStateList<TileState> = mutableStateListOf()
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
        if (board.isEmpty()) {

            val (x, y) = rect.topLeft
            val (width, height) = rect.size.div(vector.toFloat())

            List(vector) { xp ->
                List(vector) { yp ->
                    board.add(TileState(
                        position = Pair(xp, yp),
                        subgrid = Pair(xp, yp).div(dimensions.multiplier),
                        rect = Rect(
                            offset = Offset(
                                x = (width * xp) + x,
                                y = (height * yp) + y
                            ),
                            size = Size(width, height)
                        )
                    ))
                }
            }

        }
    }

    fun updateSelectedPositionWith(position: Pair<Int, Int>?) {
        position?.let {
            backStack.add(position)
        } ?: backStack.removeLastOrNull()
    }

    fun changeValue(value: String) {

        selectedPosition()?.let { position ->

            board.indexOfFirst { it.position == position }.also { index ->

                validateAndChangeValue(
                    index = index,
                    value = value,
                    sudokuBoard = board
                )

            }

            if (value.isEmpty()) {
                updateSelectedPositionWith(null)
            }
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

//    private fun fromUiBoard() : Array<Array<Int>> {
//        return board.map { row ->
//            row.map { it.value() }.toTypedArray()
//        }.toTypedArray()
//    }

    fun undoLast() { changeValue(EMPTY_TILE) }
    suspend fun clearBoard() {
        while (selectedPosition() != null) {
            delay(placementSpeed.value.time).also { undoLast() }
        }
    }

    suspend fun solveTheBoard() {
//        if (solvable()) { setBoard(findSolutionInstantly(fromUiBoard())) }
    }

    private suspend fun setBoard(solvedBoard: Array<Array<Int>>) {

//        fromUiBoard().forEachIndexed { x, ints ->
//            ints.forEachIndexed { y, i ->
//                if (i == 0) {
//                    delay(placementSpeed.value.time).also {
//                        changeValue(
//                            value = toTileText(solvedBoard[x][y]),
//                            position = Pair(x,y)
//                        )
//                    }
//                }
//            }
//        }

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

    private fun validateAndChangeValue(
        index : Int,
        value: String,
        sudokuBoard: MutableList<TileState>
    ) {

        val excluded = mutableSetOf<Pair<Int,Int>>()

        sudokuBoard.apply {

            get(index).copy(text = value).also { changed ->
                set(index, changed)
                filter { tile -> tile.x == changed.x }.valid(excluded)
                filter { tile -> tile.y == changed.y }.valid(excluded)
                filter { tile -> tile.subgrid == changed.subgrid }.valid(excluded)
                update(excluded) { it.copy(isValid = false) }
            }
        }

    }

    /**
     * [valid] extends a grouping of [TileState] in the same row, column or subgrid.
     * [valid] takes a parameter called [excluded] [MutableSet] of type [Pair] of type [Int].
     * {excluded} ensures that we don't override validation when we call multiple [valid] functions.
     * i.e row, column, or subgrid.
     *
     * 1. filter out any [TileState] objects with a empty [TileState.text] value.
     * 2. any [TileState] that contain the same [TileState.text] value are grouped together.
     * 3. each [TileState] [Grouping] is counted(how many [TileState] in each group).
     * 4. filter out any [TileState.position] contained in our [Grouping].
     * 5. for any [TileState] with a [Grouping.eachCount] [greaterThanOne] it is invalid.
     * 6. finally we place [TileState.position] within the [excluded] [Set].]
     */
    private fun List<TileState>.valid(excluded: MutableSet<Pair<Int,Int>>) {

        val occurrenceCount = this.filter { it.text.isNotEmpty() }.groupingBy { it.text }.eachCount()

        this.filter { !excluded.contains(it.position) }.forEach {
            !occurrenceCount[it.text].greaterThanOne().also { isInvalid ->
                if (isInvalid) { excluded.add(it.position) }
            }
        }

    }


    private fun MutableList<TileState>.update(
        position: Pair<Int, Int>,
        copy: (TileState) -> TileState
    ) {
        this.indexOfFirstOrNull {
            it.position == position
        }?.let { (index, tile) ->
            this[index] = copy(tile)
        }
    }

    private fun MutableList<TileState>.update(
        positions: Set<Pair<Int, Int>>,
        copy: (TileState) -> TileState
    ) {
        positions.forEach { position ->
            this.update(position) {
                copy(it)
            }
        }
    }

    fun allTilesAreValid() : Boolean {
        return board.all { tile -> tile.isValid && tile.text.isNotEmpty() }
    }

    private fun solvable() : Boolean {
        return true
//        return board.all { row ->
//            row.all { tile -> tile.isValid } && row.any { tile -> tile.text.isEmpty() }
//        }
    }
}
