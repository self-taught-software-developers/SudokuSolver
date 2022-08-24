package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.EMPTY_TILE
import kotlinx.coroutines.flow.*

class BoardState(var solved: Boolean? = null) {

    private val _selectedPosition = MutableStateFlow<Pair<Int,Int>?>(null)
    var selectedPosition : SharedFlow<Pair<Int, Int>?> = _selectedPosition.asSharedFlow()

    private val _initialBoard = MutableStateFlow(emptySudokuBoard)
    var initialBoard : SharedFlow<Array<Array<TileState>>> = _initialBoard.asSharedFlow()

    private var previousPosition : MutableList<Pair<Int, Int>?> = mutableListOf()

    fun selectPosition(position: Pair<Int, Int>) {
        _selectedPosition.update { position }
    }

    fun fromUiBoard() : Array<Array<Int>> {
        return _initialBoard.value.map { row ->
            row.map { it.value() }.toTypedArray()
        }.toTypedArray()
    }

    fun changeValue(value: String) {
        _selectedPosition.value?.let { (x, y) ->
            previousPosition.top(_selectedPosition.value)
            _initialBoard.updateCopy { it[x][y].text = value }
        }
    }

    fun undoLast() {
        _selectedPosition.value?.let { (x, y) ->
            _initialBoard.updateCopy { it[x][y].text = EMPTY_TILE }
            _selectedPosition.update { previousPosition.takeTopOrNull((_selectedPosition.value)) }
        }
    }
    fun clearBoard() {
        previousPosition.clear()
        _initialBoard.updateCopy { it.map { it.map { it.text = EMPTY_TILE } } }
        _selectedPosition.update { null }
    }

    private fun <T> MutableList<T>.takeTopOrNull(value: T) : T? {
        return if(value == lastOrNull()) {
            removeLastOrNull()
            lastOrNull()
        } else {
            lastOrNull()
        }.also { removeLastOrNull() }
    }
    private fun <T> MutableList<T>.top(value: T) {
        if (this.contains(value)) {
            this.remove(value)
            this.add(value)
        } else this.add(value)
    }
    private fun <T> MutableStateFlow<T>.updateCopy(function: (T) -> Unit) {
        when(val array = this.value) {
            is Array<*> -> this.update {
                array.copy { function(array as T) } as T
            }
        }
    }
    private fun <T> Array<T>.copy(function: (Array<T>) -> Unit) = this
        .copyOf().apply { function(this@copy) }
    companion object {
        val emptySudokuBoard = Array(9) { x ->
            Array(9) { y ->
                TileState(position = Pair(x,y))
            }
        }
        val sudokuBoardFilled = Array(9) { x ->
            Array(9) { y ->
                TileState(
                    text = x.toString(),
                    position = Pair(x,y)
                )
            }
        }
    }

}