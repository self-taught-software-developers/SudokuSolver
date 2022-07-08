package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager.SudokuSolverWorker
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.EMPTY_TILE
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.sudokuBoardArray
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class SudokuViewModel @Inject constructor(
    private val worker: SudokuSolverWorker
) : ViewModel() {

    private val _sudokuBoardStateAlt = MutableStateFlow(sudokuBoardArray)
    val sudokuBoardStateAlt : StateFlow<Array<Array<TileState>>> = _sudokuBoardStateAlt.asStateFlow()

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
            val solved = CompletableDeferred<BoardState>().apply {
                worker.solveBoard(board.convertFromUiBoard())
                    .also { deferred ->
                        complete(deferred)
                    }
            }.await()

            return@update solved.board.convertToUiBoard()

        }

    }

    private fun Array<Array<TileState>>.convertFromUiBoard() : Array<Array<Int>> {
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
    private fun Array<Array<Int>>.convertToUiBoard() : Array<Array<TileState>> {
        return this.map { row ->
            row.map { TileState(it.toString()) }.toTypedArray()
        }.toTypedArray()
    }

}