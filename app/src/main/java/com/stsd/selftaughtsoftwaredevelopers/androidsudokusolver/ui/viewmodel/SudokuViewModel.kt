package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager.SudokuSolverWorker
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.Tile
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.Tile.Companion.EMPTY_TILE
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.sudokuBoardArray
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class SudokuViewModel @Inject constructor(
    private val worker: SudokuSolverWorker
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
    fun enterNewValue(newValue: String) = viewModelScope.launch {

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

    fun clearBoard() = viewModelScope.launch {

        _sudokuBoardStateAlt.update { board ->
            val copy = board.copyOf()

            copy.forEach { row ->
                row.map { it.text = EMPTY_TILE }
            }

            return@update copy
        }
    }

    fun solveBoard() = viewModelScope.launch(Dispatchers.Default) {

        _sudokuBoardStateAlt.update { board ->
            val solved = CompletableDeferred<Pair<Array<Array<Int>>, Boolean>>().apply {
                complete(worker.solveBoard(board.convertFromUiBoard()))
            }.await()

            return@update solved.first.map { row ->
                row.map { Tile(it.toString()) }.toTypedArray()
            }.toTypedArray()

        }

    }

    private fun Array<Array<Tile>>.convertFromUiBoard() : Array<Array<Int>> {
        return this.map { row ->
            row.map {
                if(it.text == EMPTY_TILE) {
                    0
                } else {
                    it.text.toInt()
                }
            }.toTypedArray()
        }.toTypedArray()

    }

    companion object {
        fun newEntryTest() = Random.nextInt(0, 9).toString()
        const val TAG = "SudokuViewModel_TAG"
    }

}