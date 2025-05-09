package com.stsd.selftaughtsoftwaredevelopers.shared.ui.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerve.development.ui.canvas.model.CerveCanvasInteractionType
import com.cerve.development.ui.canvas.model.CerveCanvasState
import com.cerve.development.ui.state.helper.UIInitStateInstance.Companion.InitMutableUIStateFlow
import com.cerve.development.ui.state.helper.UIInitStateInstance.Companion.asStateFlow
import com.cerve.development.ui.state.helper.UIInitStateInstance.Companion.getState
import com.cerve.development.ui.state.observer.UIInitStateInstanceUpdate.stateOrNullScope
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.BoardState
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.PlacementOrigin
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.TileState
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.isValidPlacement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SudokuSolverViewModel : ViewModel() {

    private val _uiState = InitMutableUIStateFlow {
        val canvasState = CerveCanvasState(
            gridLineCount = 9,
            initialInteractionType = CerveCanvasInteractionType.CellSingleSelector
        )

        BoardState(canvasState = canvasState)
    }
    val uiState = _uiState.asStateFlow()

    fun changeValue(value: Int) = viewModelScope.launch(Dispatchers.IO) {
        _uiState.getState?.let { state ->
            state.canvasState.selectedCell?.let { cell ->

                val position = cell.position(state.dimensions.multiplier)
                val candidate = TileState(
                    value = value,
                    position = position,
                    origin = PlacementOrigin.User
                )

                val isValidPlacement = isValidPlacement(
                    candidate = candidate,
                    board = state.board
                )

                val tileState = if (isValidPlacement) {
                    candidate
                } else candidate.copy(origin = PlacementOrigin.UserError)

                state.upsert(tile = tileState)
            }
        }
    }

    fun delete() = viewModelScope.launch(Dispatchers.IO)  {
        _uiState.getState?.delete()
    }

    fun reset() = viewModelScope.launch(Dispatchers.IO)  {
        _uiState.getState?.reset()
    }

    fun solveBoard() = _uiState.stateOrNullScope {
        findSolution(board)
        this
    }
}
