package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.sudokuBoard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class SudokuViewModel @Inject constructor(

) : ViewModel() {

    val sudokuBoardState = mutableStateListOf(sudokuBoard)

    private val _selectedPosition = MutableStateFlow(Pair(1, 1))
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

        val position = selectedPosition.value
        val board = sudokuBoardState[0].toMutableList()
        val row = board[position.first].toMutableList()
        row[position.second].text = newValue
        board[position.first] = row

        sudokuBoardState.clear()
        sudokuBoardState.add(board)

    }

    companion object {
        fun newEntryTest() = Random.nextInt(0, 9).toString()
    }

}