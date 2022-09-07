package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager.StoragePreferences
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.GridState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.ScannerState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SudokuViewModel @Inject constructor(
    private val storagePreferences: StoragePreferences
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

}