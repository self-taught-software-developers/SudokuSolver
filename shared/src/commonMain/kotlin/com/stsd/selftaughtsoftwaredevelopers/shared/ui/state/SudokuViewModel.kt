package com.stsd.selftaughtsoftwaredevelopers.shared.ui.state

//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager.StoragePreferences
//import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardActivityState
//import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardActivityState.LOADED
//import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.BoardState
//import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.collectLatest
//import kotlinx.coroutines.flow.update
//import kotlinx.coroutines.launch
//import org.koin.android.annotation.KoinViewModel
//
//@KoinViewModel
//class SudokuViewModel(
//    private val storagePreferences: StoragePreferences
//) : ViewModel() {
//
//    private val boardState = MutableStateFlow(BoardState())
//    val uiBoardState = boardState.asStateFlow()
//
//    private var activelyPlacing: Boolean = false
//
//    init {
//
//        viewModelScope.launch {
//            storagePreferences.solutionSpeed.collectLatest { speed ->
//                boardState.value.updatePlacementSpeed(speed)
//            }
//        }
//    }
//
//    fun loadingCompleted(state: BoardActivityState = LOADED) {
//        boardState.update { it.copy(state = state) }
//    }
//
//    fun updatePlacementSpeed(value: TimeState) = viewModelScope.launch {
//        storagePreferences.updateSolutionSpeed(value)
//    }
//
//    /**
//     * Modify BoardState
//     */
//    fun updateBoardWithSolution() = viewModelScope.launch {
//        readyToPlace { boardState.value.solveTheBoard() }
//    }
//
//    fun updateSelectedValue(value: String) = viewModelScope.launch {
//        readyToPlace { uiBoardState.value.changeValue(value) }
//    }
//
//    fun undoLast() = viewModelScope.launch {
//        readyToPlace { uiBoardState.value.undoLast() }
//    }
//
//    fun undoAll() = viewModelScope.launch {
//        readyToPlace { uiBoardState.value.undoAll() }
//    }
//
//    private suspend fun readyToPlace(action: suspend () -> Unit) {
//        if (!activelyPlacing) {
//            activelyPlacing = true
//            action()
//            activelyPlacing = false
//        }
//    }
//}
