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
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SudokuViewModel @Inject constructor(
    private val storagePreferences: StoragePreferences
) : ViewModel() {

    private val boardState = MutableStateFlow(BoardState(state = LOADING))
    val uiBoardState = boardState.asStateFlow()

    private var activelyPlacing: Boolean = false

    init {

        viewModelScope.launch {
            storagePreferences.solutionSpeed.collectLatest { speed ->
                boardState.update { oldState ->
                    oldState.copy(placementSpeed = speed)
                }
            }
        }

    }

    fun loadingCompleted(state: BoardActivityState = LOADED) {
        boardState.update { it.copy(state = state) }
    }

    fun updatePlacementSpeed(value: TimeState) = viewModelScope.launch {
        storagePreferences.updateSolutionSpeed(value)
    }

    /**
     * Modify BoardState
     */
    fun updateBoardWithSolvedPlacements() = viewModelScope.launch {
        readyToPlace { boardState.value.solveTheBoard() }
    }

    fun updateSelectedValue(value: String) = viewModelScope.launch {
        readyToPlace { uiBoardState.value.changeValue(value) }
    }

    fun undoLast() = viewModelScope.launch {
        readyToPlace { uiBoardState.value.undoLast() }
    }

    fun undoAll() = viewModelScope.launch {
        readyToPlace { uiBoardState.value.clearBoard() }
    }


    private suspend fun readyToPlace(action: suspend () -> Unit) {
        if(!activelyPlacing) {
            activelyPlacing = true
            action()
            activelyPlacing = false
        }
    }

}
