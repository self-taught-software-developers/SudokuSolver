package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.Tile
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.Tile.Companion.EMPTY_TILE
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

    private val _sudokuBoardStateAlt = MutableStateFlow(sudokuBoardArray)
    val sudokuBoardStateAlt : StateFlow<Array<Array<Tile>>> = _sudokuBoardStateAlt.asStateFlow()

    private val _selectedPosition = MutableStateFlow(Pair(0, 0))
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

        _sudokuBoardStateAlt.update { board ->

            val position = selectedPosition.value
            val copy = board.copyOf()

            copy[position.first][position.second].text = EMPTY_TILE

            return@update copy
        }

    }

    fun clearBoard()  {

        _sudokuBoardStateAlt.update { board ->
            val copy = board.copyOf()

            copy.forEach { row ->
                row.map { it.text = EMPTY_TILE }
            }

            return@update copy
        }
    }

    fun solveBoard() = viewModelScope.launch {
        Log.d(TAG, "solveBoard")


    }

    companion object {
        fun newEntryTest() = Random.nextInt(0, 9).toString()
        const val TAG = "SudokuViewModel_TAG"
    }

}