package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager.SudokuSolverWorker
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.Tile
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.sudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.sudokuBoardArray
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class SudokuViewModel @Inject constructor(
//    private val worker: SudokuSolverWorker
) : ViewModel() {

    val sudokuBoardState = mutableStateListOf(sudokuBoard)

    private val _sudokuBoardStateAlt = MutableStateFlow(sudokuBoardArray)
    val sudokuBoardStateAlt : StateFlow<Array<Array<Tile>>> = _sudokuBoardStateAlt.asStateFlow()

    private val _selectedPosition = MutableStateFlow(Pair(-1, -1))
    val selectedPosition : StateFlow<Pair<Int, Int>> = _selectedPosition.asStateFlow()

    fun updateSelectedPosition(
        newPosition: Pair<Int, Int>
    ) = viewModelScope.launch {
        _selectedPosition.update { newPosition }
        /*
            tests new value entry without numbered buttons being clicked.
            enterNewValue(newEntryTest())
         */
    }

    /**
     * Call this function when a numbered button is clicked.
     */
    fun enterNewValue(
        newValue: String
    ) = viewModelScope.launch {

        _sudokuBoardStateAlt.update { board ->

            val position = selectedPosition.value
            val copy = board.copyOf()

            copy[position.first][position.second].text = newValue
            return@update copy

        }

    }

    fun unDoRecentChange() = viewModelScope.launch {

    }

    fun clearBoard() = viewModelScope.launch {

    }

    fun solveBoard() = viewModelScope.launch {


    }

    companion object {
        fun newEntryTest() = Random.nextInt(0, 9).toString()
        const val TAG = "SudokuViewModel_TAG"
    }

}