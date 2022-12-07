package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.checkValidity
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.div
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.findEmptyPosition
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.validatePlacement
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.EMPTY_TILE
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.toTileText
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.successGreen500
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.chunked
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class BoardState(
    val dimensions: GridState = GridState.GRID_3X3,
    val state: BoardActivityState = BoardActivityState.LOADING
) {

    val board: SnapshotStateList<TileState> = mutableStateListOf()

    private val placementBackStack: SnapshotStateList<Position?> = mutableStateListOf()
    private val _placementSpeed = MutableStateFlow(TimeState.DEFAULT_SPEED)
    val placementSpeed: StateFlow<TimeState> = _placementSpeed.asStateFlow()

    fun updatePlacementSpeed(value: TimeState) = _placementSpeed.update { value }

    fun selectedPosition() = placementBackStack.lastOrNull()
    fun isLoading() = state == BoardActivityState.LOADING

    @Composable
    fun color() = if (this.allTilesAreValid()) {
        successGreen500
    } else { ExtendedTheme.colors.primary }

    init {

        if (board.isEmpty()) {

            List(dimensions.vector()) { xp ->
                List(dimensions.vector()) { yp ->
                    board.add(
                        TileState(
                            position = Position(x = xp,y = yp),
                            subgrid = Position(x = xp, y = yp).div(dimensions.multiplier)
                        )
                    )
                }
            }
        }
    }

    fun updateSelectedPositionWith(position: Position?) {
        position?.let {
            placementBackStack.add(position)
        } ?: placementBackStack.removeLastOrNull()
    }

    fun changeValue(value: String) {
        selectedPosition()?.let { position ->

            board.indexOfFirst { it.position == position }.also { index ->
                validateAndChangeValue(
                    changedIndex = index,
                    value = value,
                    sudokuBoard = board
                )
            }

            if (value.isEmpty()) {
                updateSelectedPositionWith(null)
            }
        }
    }
    private fun changeValue(value: String, position: Position) {
        if (value.isEmpty()) {
            updateSelectedPositionWith(null)
        } else {
            updateSelectedPositionWith(position)
        }

        board.indexOfFirst { it.position == position }.also { index ->
            validateAndChangeValue(
                changedIndex = index,
                value = value,
                sudokuBoard = board
            )
        }
    }

    private fun fromUiBoard(): Array<Array<Int>> {
        return board.map { it.value() }.chunked(dimensions.vector()).toTypedArray()
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
                            position = Position(x = x, y = y)
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
        changedIndex: Int,
        value: String,
        sudokuBoard: MutableList<TileState>
    ) {

        /**
         * for each tile check if it's valid then move to next tile.
         */

        sudokuBoard.apply {
            this[changedIndex] = get(changedIndex).copy(text = value)
            val comparitor = map { it.value() }.chunked(9).toTypedArray()

            (sudokuBoard.indices).forEach { index ->
                 get(index).also { modified ->

                     this[index] = modified.copy(
                        isValid = checkValidity(
                            number = modified.value(),
                            position = modified.position,
                            board = comparitor
                        )
                    )
                }

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
