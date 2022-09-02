package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager.StoragePreferences
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager.SudokuSolverWorker
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.GridState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.ScannerState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SudokuViewModel @Inject constructor(
    private val storagePreferences: StoragePreferences,
    private val worker: SudokuSolverWorker
) : ViewModel() {

    val solutionSpeed = storagePreferences.solutionSpeed
    val gridDimension = storagePreferences.gridDimensions

    fun updateSolutionSpeed(value: TimeState) = viewModelScope.launch {
        storagePreferences.updateSolutionSpeed(value)
    }

    fun updateGridDimension(value: GridState) = viewModelScope.launch {
        storagePreferences.updateGridDimensions(value)
    }


    private val _scannerState = MutableStateFlow(ScannerState.OFF)
    val scannerState : StateFlow<ScannerState> = _scannerState.asStateFlow()

    fun toggleCameraState() {
        _scannerState.value = when(_scannerState.value) {
            ScannerState.OFF -> ScannerState.IDLE
            ScannerState.IDLE -> ScannerState.OFF
            ScannerState.SCANNING -> ScannerState.OFF
        }
    }

//    fun updateSelectedPosition(
//        newPosition: Pair<Int, Int>
//    ) = _sudokuBoardStateAlt.value.selectPosition(newPosition)
//
//    /**
//     * Call this function when a numbered button is clicked.
//     */
//    fun enterNewValue(
//        newValue: String
//    ) = _sudokuBoardStateAlt.value.changeValue(newValue)
//
//    fun enterNewValue(
//        newValue: String,
//        position: Pair<Int, Int>
//    ) = _sudokuBoardStateAlt.value.changeValue(newValue, position)
//
//    fun undoLast() = _sudokuBoardStateAlt.value.undoLast()
//    fun clearBoard() = _sudokuBoardStateAlt.value.clearBoard()
//
//    fun solveBoard() = viewModelScope.launch(Dispatchers.Default) {
//
//        worker.solveBoard(_sudokuBoardStateAlt.value)
//
//    }

}