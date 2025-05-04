package com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.div
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.findEmptyPosition
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.validatePlacement
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.Position
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.SolutionState
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.TimeState
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.collections.get

data class BoardState(
    val dimensions: GridState = GridState.GRID_3X3,
) {
    val board: SnapshotStateList<TileState> = mutableStateListOf()
    private val placementBackStack: SnapshotStateList<Position?> = mutableStateListOf()

    init {

//        if (board.isEmpty()) {
//            List(dimensions.vector()) { x ->
//                List(dimensions.vector()) { y ->
//                    Position(x = x, y = y).let { position ->
//                        board.add(
//                            TileState(
//                                position = position,
//                                subgrid = position.div(dimensions.multiplier)
//                            )
//                        )
//                    }
//                }
//            }
//        }
    }

//    private val _solutionState = MutableStateFlow(SolutionState.IDLE)
//    val solutionState = _solutionState.asStateFlow()
//
//
//    private val _placementSpeed = MutableStateFlow(TimeState.DEFAULT_SPEED)
//    val placementSpeed: StateFlow<TimeState> = _placementSpeed.asStateFlow()
//
//    fun updatePlacementSpeed(value: TimeState) = _placementSpeed.update { value }
//
//    fun selectedPosition(position: Position? = null): Position? {
//        position?.let { placementBackStack.add(position) }
//        return placementBackStack.lastOrNull()
//    }
//
//    /**
//     * @[updateSelectedPosition] takes a nullable @[Position].
//     *
//     * When null is passed for the position parameter.
//     * The last item in @[placementBackStack] is removed, this updates the @[selectedPosition] to
//     * it's previous position.
//     *
//     * When a value is passed for the position parameter.
//     * That value is added to the top of our @[placementBackStack], this updates the
//     * @[selectedPosition] to it's previous position.
//     */
//    fun updateSelectedPosition(position: Position?) {
//        position?.let {
//            placementBackStack.add(position)
//        } ?: placementBackStack.removeLastOrNull()
//    }
//
//    fun changeValue(value: String, position: Position? = null) {
//        selectedPosition(position)?.let { selectedPosition ->
//
//            board.apply {
//                val index = indexOfFirst { it.position == selectedPosition }
//                val changedTile = get(index).copy(text = value, isValid = true)
//
//                set(index, changedTile)
//            }
//
//            if (value.isEmpty()) {
//                updateSelectedPosition(null)
//            }
//        }
//        _solutionState.update { SolutionState.IDLE }
//    }
//
//    fun undoLast() {
//        changeValue(TileState.Companion.EMPTY_TILE)
//    }
//
//    suspend fun undoAll() {
//        while (selectedPosition() != null) {
//            delay(placementSpeed.value.time)
//            undoLast()
//        }
//    }
//
//    suspend fun solveTheBoard() {
////        if (solvable()) {
////            setBoard(findSolutionInstantly(fromUiBoard()))
////        } else {
////            _solutionState.update { SolutionState.ERROR }
////        }
//    }
//
//    private suspend fun setBoard(solvedBoard: Array<Array<Int>>) {
//        fromUiBoard().forEachIndexed { x, ints ->
//            ints.forEachIndexed { y, i ->
//                if (i == 0) {
//                    delay(placementSpeed.value.time).also {
//                        changeValue(
//                            value = TileState.Companion.toTileText(solvedBoard[x][y]),
//                            position = Position(x = x, y = y)
//                        )
//                    }
//                }
//            }
//        }
//        _solutionState.update { SolutionState.SUCCESS }
//    }
//    private fun findSolutionInstantly(board: Array<Array<Int>>): Array<Array<Int>> {
//        findEmptyPosition(board).also { position ->
//            if (position.isEmpty()) {
//                return board
//            } else {
//                (1..9).forEach { candidate ->
//                    if (validatePlacement(board, candidate, position)) {
//                        board[position[0]][position[1]] = candidate
//
//                        if (findEmptyPosition(findSolutionInstantly(board)).isEmpty()) return board
//
//                        board[position[0]][position[1]] = 0
//                    }
//                }
//                return board
//            }
//        }
//    }
//    private fun fromUiBoard(): Array<List<Int>> {
//        return board
//            .map { it.value() }
//            .chunked(dimensions.vector())
//            .toTypedArray()
//    }

//    private suspend fun solvable(): Boolean {
//        return CompletableDeferred<Boolean>().apply {
//            board.apply {
//                (indices).forEach { index ->
//
////                    get(index).also { tile ->
////                        this[index] = get(index).copy(
////                            isValid = validatePlacement(
////                                position = tile.position,
////                                number = tile.value(),
////                                board = fromUiBoard()
////                            )
////                        )
////                    }
//                }
//                val result = board.all { it.isValid } && board.any { it.text.isEmpty() }
//                println(result)
//                complete(result)
//            }
//        }.await()
//    }
}