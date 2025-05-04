package com.stsd.selftaughtsoftwaredevelopers.shared.ui.state

import androidx.lifecycle.ViewModel
import com.cerve.development.ui.canvas.model.CerveCanvasInteractionType
import com.cerve.development.ui.canvas.model.CerveCanvasLineData
import com.cerve.development.ui.canvas.model.CerveCanvasState
import com.cerve.development.ui.canvas.operators.CerveCanvasDefaults
import com.cerve.development.ui.state.helper.UIInitStateInstance.Companion.InitMutableUIStateFlow
import com.cerve.development.ui.state.helper.UIInitStateInstance.Companion.asStateFlow
import com.cerve.development.ui.state.helper.UIInitStateInstance.Companion.getState
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.BoardState
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SudokuSolverViewModel : ViewModel() {

    private val _uiState = InitMutableUIStateFlow {
        CerveCanvasState(
            initialLines = CerveCanvasDefaults.canvasLines.copy(
                gridLineCount = 9
            ),
            initialInteractionType = CerveCanvasInteractionType.CellSingleSelector
        )
    }
    val uiState = _uiState.asStateFlow()

    fun changeValue(value: String) {
//        _uiState.getState?.changeValue(value)
    }
}