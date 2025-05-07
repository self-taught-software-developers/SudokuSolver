package com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.geometry.Offset
import com.cerve.development.ui.canvas.model.CerveCanvasState
import com.cerve.development.ui.canvas.model.CerveCell
import com.cerve.development.ui.canvas.model.CerveOffset.Companion.offset
import com.cerve.development.ui.canvas.model.CerveSize
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.component.findEmptyPosition
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.component.isValidPlacement
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.Position
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.Position.Companion.findPosition
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.SolutionState
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.TimeState
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class BoardState(
    val dimensions: GridState = GridState.GRID_3X3,
    val canvasState: CerveCanvasState
) {
    val board = List(dimensions.cellCount) { index ->
        TileState(
            point = index.findPosition(
                dimensions.multiplier,
                dimensions.vector
            )
        )
    }.toMutableStateList()


    private val placementBackstack: SnapshotStateList<Int?> = mutableStateListOf()
    private val placementHistory: SnapshotStateList<Int?> = mutableStateListOf()

    private val _solutionState = MutableStateFlow(SolutionState.IDLE)
    val solutionState = _solutionState.asStateFlow()

    private val _placementSpeed = MutableStateFlow(TimeState.DEFAULT_SPEED)
    val placementSpeed: StateFlow<TimeState> = _placementSpeed.asStateFlow()

    fun updatePlacementSpeed(value: TimeState) = _placementSpeed.update { value }

    fun upsert(at: Int? = null, tile: TileState) {

        val index = at ?: board.indexOfFirst { element ->
            element.point == tile.point
        }

        if (index != -1) {
            if (placementBackstack.contains(index)) {
                placementBackstack.removeAt(index)
            } else placementBackstack.add(index)
            board[index] = tile
        }
    }

    fun redoLast() {
//        changeValue(TileState.Companion.EMPTY_TILE)
    }

    fun undoLast() {
        placementBackstack.lastOrNull()?.let { index ->
            val tile = board[index].copy(value = 0)
            upsert(at = index, tile)
        }
    }

    suspend fun undoAll() {
        board.forEach {
            delay(TimeState.INSTANT_SPEED.time)
            undoLast()
        }
    }

    fun findSolutionInstantly(board: MutableList<TileState>): List<TileState> {
        findEmptyPosition(board).also { position ->
            if (position == null) {
                return board
            } else {
                (1..9).forEach { value ->
                    val candidate = TileState(
                        value = value,
                        point = position
                    )
                    if (isValidPlacement(candidate, board)) {

                        val index = board.indexOfFirst { it.point == position }
                        upsert(index, candidate)

                        if (findEmptyPosition(findSolutionInstantly(board)) == null) {
                            return board
                        }

                        upsert(index, candidate.copy(value = 0))

                    }
                }
                return board
            }
        }
    }

}