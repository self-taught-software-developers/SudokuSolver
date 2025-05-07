package com.stsd.selftaughtsoftwaredevelopers.shared.ui.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerve.development.ui.canvas.model.CerveCanvasInteractionType
import com.cerve.development.ui.canvas.model.CerveCanvasState
import com.cerve.development.ui.canvas.operators.CerveCanvasDefaults
import com.cerve.development.ui.state.helper.UIInitStateInstance.Companion.InitMutableUIStateFlow
import com.cerve.development.ui.state.helper.UIInitStateInstance.Companion.asStateFlow
import com.cerve.development.ui.state.helper.UIInitStateInstance.Companion.getState
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.Position
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.BoardState
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.TileState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import kotlin.math.roundToInt

@KoinViewModel
class SudokuSolverViewModel : ViewModel() {

    private val _uiState = InitMutableUIStateFlow {
        val canvasState = CerveCanvasState(
            initialLines = CerveCanvasDefaults
                .canvasLines.copy(gridLineCount = 9),
            initialInteractionType = CerveCanvasInteractionType.CellSingleSelector
        )

        BoardState(canvasState = canvasState)
    }
    val uiState = _uiState.asStateFlow()

    fun changeValue(value: Int) {
        _uiState.getState?.let { state ->
            state.canvasState.selectedCells.lastOrNull()?.let { cell ->

                val point = Position(
                    row = (cell.offset.y.div(cell.size.width).roundToInt()),
                    column = cell.offset.x.div(cell.size.height).roundToInt(),
                    multiplier = state.dimensions.multiplier
                )

                state.upsert(
                    tile = TileState(
                        value = value,
                        point = point
                    )
                )
            }
        }
    }

    fun undoLast() = viewModelScope.launch(Dispatchers.IO) {
        _uiState.getState?.undoLast()
    }

    fun redoLast() = viewModelScope.launch(Dispatchers.IO)  {
        _uiState.getState?.redoLast()
    }

    fun cleaAll() = viewModelScope.launch(Dispatchers.IO)  {
        _uiState.getState?.undoAll()
    }

    fun solveBoard() = viewModelScope.launch(Dispatchers.IO) {
        _uiState.getState?.let { state ->
            println(state.board.map { it.value })
            state.findSolutionInstantly(state.board)
        }
    }
}
