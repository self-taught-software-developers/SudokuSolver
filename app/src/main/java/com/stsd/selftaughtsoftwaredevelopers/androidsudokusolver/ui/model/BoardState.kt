package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.div
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.findEmptyPosition
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.indexOfFirstOrNull
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.validatePlacement
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.vector
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.EMPTY_TILE
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.toTileText
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.successGreen500
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.chunked
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.greaterThanOne
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class BoardState(
    val dimensions: GridState = GridState.GRID_3X3,
    val state: BoardActivityState = BoardActivityState.LOADING
) {
    val vector = dimensions.multiplier.vector()

    val board: SnapshotStateList<TileState> = mutableStateListOf()
    private val backStack: SnapshotStateList<Pair<Int, Int>?> = mutableStateListOf()

    private val _placementSpeed = MutableStateFlow(TimeState.DEFAULT_SPEED)
    var placementSpeed: StateFlow<TimeState> = _placementSpeed.asStateFlow()

    fun updatePlacementSpeed(value: TimeState) = _placementSpeed.update { value }

    fun selectedPosition() = backStack.lastOrNull()
    fun isLoading() = state == BoardActivityState.LOADING

    @Composable
    fun color() = if (this.allTilesAreValid()) {
        successGreen500
    } else { ExtendedTheme.colors.primary }

    init {

        if (board.isEmpty()) {

            List(vector) { xp ->
                List(vector) { yp ->
                    board.add(
                        TileState(
                            position = Pair(xp, yp),
                            subgrid = Pair(xp, yp).div(dimensions.multiplier)
                        )
                    )
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
        if (value.isEmpty()) {
            updateSelectedPositionWith(null)
        } else {
            updateSelectedPositionWith(position)
        }

        board.indexOfFirst { it.position == position }.also { index ->
            validateAndChangeValue(
                index = index,
                value = value,
                sudokuBoard = board
            )
        }
    }

    private fun fromUiBoard(): Array<Array<Int>> {
        return board.map { it.value() }.chunked(vector).toTypedArray()
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
                            position = Pair(x, y)
                        )
                    }
                }
            }
        }
    }
    private fun findSolutionInstantly(board: Array<Array<Int>>): Array<Array<Int>> {
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
        index: Int,
        value: String,
        sudokuBoard: MutableList<TileState>
    ) {
        val excluded = mutableSetOf<Pair<Int, Int>>()
        val included = mutableSetOf<Pair<Int, Int>>()

        sudokuBoard.apply {
            get(index).copy(text = value).also { changed ->
                set(index, changed)
                filter { tile -> tile.x == changed.x }.valid(excluded, included)
                filter { tile -> tile.y == changed.y }.valid(excluded, included)
                filter { tile -> tile.subgrid == changed.subgrid }.valid(excluded, included)

                update(excluded) { it.copy(isValid = false) }
                update(included.filter { !excluded.contains(it) }.toSet()) { it.copy(isValid = true) }
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
    private fun List<TileState>.valid(
        excluded: MutableSet<Pair<Int, Int>>,
        included: MutableSet<Pair<Int, Int>>
    ) {
        val filteredList = this.filter { it.text.isNotEmpty() }
        val occurrenceCount = filteredList.groupingBy { it.text }.eachCount()

        filteredList.filter {
            !excluded.contains(it.position)
        }.forEach {
            !occurrenceCount[it.text].greaterThanOne().also { isInvalid ->
                if (isInvalid) {
                    excluded.add(it.position)
                } else {
                    included.add(it.position)
                }
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

    private fun allTilesAreValid(): Boolean {
        return board.all { tile -> tile.isValid && tile.text.isNotEmpty() }
    }

    private fun solvable(): Boolean {
        return board.all { tile -> tile.isValid } && board.any { tile -> tile.text.isEmpty() }
    }
}
