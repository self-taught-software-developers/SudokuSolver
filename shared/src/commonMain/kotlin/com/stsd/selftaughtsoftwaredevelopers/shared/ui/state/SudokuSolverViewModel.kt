package com.stsd.selftaughtsoftwaredevelopers.shared.ui.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerve.development.ui.canvas.model.CerveCanvasInteractionType
import com.cerve.development.ui.canvas.model.CerveCanvasLineData
import com.cerve.development.ui.canvas.model.CerveCanvasState
import com.cerve.development.ui.canvas.operators.CerveCanvasDefaults
import com.cerve.development.ui.state.helper.UIInitStateInstance.Companion.InitMutableUIStateFlow
import com.cerve.development.ui.state.helper.UIInitStateInstance.Companion.asStateFlow
import com.cerve.development.ui.state.helper.UIInitStateInstance.Companion.getState
import com.cerve.development.ui.state.observer.UIInitStateInstanceUpdate.update
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.BoardState
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.TileState
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

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
//                state.upsert(
//                    TileState(
//                        value = value,
//                        position = cell.offset
//                    )
//                )
            }

        }
    }

    fun solveBoard() = viewModelScope.launch {
        _uiState.getState?.let { state ->
            state.solveSudokuListNullableInt(state.board)
            println(state.board.map { it.value })
            println(state.board.map { it.point })
        }
    }
}
