package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager.StoragePreferences
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardActivityState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardActivityState.LOADED
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardActivityState.LOADING
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SudokuViewModel @Inject constructor(
    private val storagePreferences: StoragePreferences
) : ViewModel() {

    private val boardState = MutableStateFlow(BoardState(state = LOADING, scope = viewModelScope))
    val uiBoardState = boardState.asStateFlow()

    init {

        viewModelScope.launch {
            storagePreferences.solutionSpeed.collectLatest { speed ->
                boardState.update {
                    it.copy(placementSpeed = speed)
                }
            }
        }

    }

    fun boardIsLoaded(state: BoardActivityState = LOADED) {
        boardState.update { it.copy(state = state) }
    }

    fun updatePlacementSpeed(value: TimeState) = viewModelScope.launch {
        storagePreferences.updateSolutionSpeed(value)
    }


//
//    val gridDimension = storagePreferences.gridDimensions
//
//    fun updateGridDimension(value: GridState) = viewModelScope.launch {
//        storagePreferences.updateGridDimensions(value)
//    }
//
//    private val _scannerState = MutableStateFlow(ScannerState.OFF)
//    val scannerState : StateFlow<ScannerState> = _scannerState.asStateFlow()
//
//    fun toggleCameraState() {
//        _scannerState.value = when(_scannerState.value) {
//            ScannerState.OFF -> ScannerState.IDLE
//            ScannerState.IDLE -> ScannerState.OFF
//            ScannerState.SCANNING -> ScannerState.OFF
//        }
//    }

}
