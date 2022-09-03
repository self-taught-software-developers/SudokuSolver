package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.calculateBoardDimensions
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.calculatePx
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.pow
import kotlin.math.sqrt

class BoardState(var dimensions: GridState) {

    var resolution: Pair<Float, Float>? = null
    var vector = dimensions.multiplier.toFloat().pow(2).toInt()

    val tiles = mutableListOf<TileState>()
    val board = mutableStateListOf<List<TileState>>()

    @Composable
    fun calculateTileDimensions(scope : BoxWithConstraintsScope): BoardState {

        scope.apply {
            resolution = calculatePx()
            calculateBoardDimensions().apply {

                if (tiles.isEmpty() || sqrt(tiles.size.toFloat()).toInt() != vector) {
                    val (x, y) = this.topLeft
                    val (width, height) = this.size.div(vector.toFloat())

                    (0 until vector).forEach { xp ->
                        (0 until vector).forEach { yp ->
                            tiles.add(
                                TileState(
                                    position = Pair(xp, yp),
                                    rect = Rect(
                                        offset = Offset(
                                            x = (width * xp) + x,
                                            y = (height * yp) + y
                                        ),
                                        size = Size(width, height)
                                    )
                                )
                            )
                        }
                    }

                    board.addAll(toBoardLayout())
                }

            }
        }.also { return this@BoardState }

    }

    private fun toBoardLayout(): List<List<TileState>> = tiles.chunked(vector)

    private val _solved = MutableStateFlow<Boolean?>(null)
    val solved : StateFlow<Boolean?> = _solved.asStateFlow()

    private val _selectedPosition = MutableStateFlow<Pair<Int,Int>?>(null)
    var selectedPosition : StateFlow<Pair<Int, Int>?> = _selectedPosition.asStateFlow()




    private var previousPosition : MutableList<Pair<Int, Int>?> = mutableListOf()

//    fun updateSolutionState(value: Boolean? = null) {
//        _solved.update { value }
//    }
    fun updateSelected(position: Pair<Int, Int>) {
        _selectedPosition.update { position }
    }
//    fun changeValue(value: String) {
//        _selectedPosition.value?.let { (x, y) ->
//            previousPosition.top(_selectedPosition.value)
//            _initialBoard.updateCopy { it[x][y].text = value }
//        }
//    }
//    fun changeValue(
//        value: String,
//        position: Pair<Int, Int>
//    ) {
//
//        selectPosition(position)
//        changeValue(value)
//
//    }
//
//    fun undoLast() {
//        _selectedPosition.value?.let { (x, y) ->
//            if (solved.value != null) updateSolutionState()
//            _initialBoard.updateCopy { it[x][y].text = EMPTY_TILE }
//            _selectedPosition.update { previousPosition.takeTopOrNull((_selectedPosition.value)) }
//        }
//    }
//    fun clearBoard() {
//        if (solved.value != null) updateSolutionState()
//        previousPosition.clear()
//        _initialBoard.updateCopy { it.map { it.map { it.text = EMPTY_TILE } } }
//        _selectedPosition.update { null }
//    }
//
//    fun isValid() : Boolean {
//        //TODO solve isn't changing in th ui when the board is invalid when we click solve
//        // first verify if a row is valid. that's done by filtering all empty positions and calling a distinct on the board.
//        // if the filtered board is not the same size as the distinct board then we know we have repeating values in our row.
//
//        fromUiBoard().apply {
//            forEach { row ->
//                row.filter { it != 0 }.also { nonEmptyPositions ->
//                    if (nonEmptyPositions.distinct().size < nonEmptyPositions.size) {
//                        _solved.update { false }
//                        return false
//                    }
//                }
//            }
//
//            // to verify the column we need to  create a bucket per column and compare the items in that column to see if they contain any distinct values.
//            var bucket = (0..lastIndex).map { arrayListOf<Int>() }
//
//            forEach { row ->
//                row.forEachIndexed { index, value ->
//                    if (value != 0) bucket[index].add(value)
//                }
//            }
//
//            if(bucket.any { it.distinct().size < it.size }) {
//                _solved.update { false }
//                return false
//            }
//            bucket = (0..lastIndex).map { arrayListOf() }
//
//            // to verify that our 3 by 3 grid doesn't have any repeating values we need ot view our 3 by 3 grid
//            forEachIndexed { index, row ->
//
//                val x = index / 3
//
//                row.forEachIndexed { columnIndex, value ->
//                    if (value != 0) {
//                        val y = columnIndex / 3
//
//                        when (listOf(x, y)) {
//                            listOf(0, 0) -> bucket[0].add(value)
//                            listOf(0, 1) -> bucket[1].add(value)
//                            listOf(0, 2) -> bucket[2].add(value)
//                            listOf(1, 0) -> bucket[3].add(value)
//                            listOf(1, 1) -> bucket[4].add(value)
//                            listOf(1, 2) -> bucket[5].add(value)
//                            listOf(2, 0) -> bucket[6].add(value)
//                            listOf(2, 1) -> bucket[7].add(value)
//                            listOf(2, 2) -> bucket[8].add(value)
//                        }
//                    }
//                }
//            }
//
//            if(bucket.any { it.distinct().size < it.size }) {
//                _solved.update { false }
//                return false
//            }
//            return true
//
//        }
//
//    }
//
//    fun fromUiBoard() : Array<Array<Int>> {
//        return _initialBoard.value.map { row ->
//            row.map { it.value() }.toTypedArray()
//        }.toTypedArray()
//    }
//    private fun <T> MutableList<T>.takeTopOrNull(value: T) : T? {
//        return if(value == lastOrNull()) {
//            removeLastOrNull()
//            lastOrNull()
//        } else {
//            lastOrNull()
//        }.also { removeLastOrNull() }
//    }
//    private fun <T> MutableList<T>.top(value: T) {
//        if (this.contains(value)) {
//            this.remove(value)
//            this.add(value)
//        } else this.add(value)
//    }
//    private fun <T> MutableStateFlow<T>.updateCopy(function: (T) -> Unit) {
//        when(val array = this.value) {
//            is Array<*> -> this.update {
//                array.copy { function(array as T) } as T
//            }
//        }
//    }
//    private fun <T> Array<T>.copy(function: (Array<T>) -> Unit) = this
//        .copyOf().apply { function(this@copy) }
//
//    companion object {
//        val emptySudokuBoard = { multiplier: Int ->
//            val shape = multiplier * multiplier
//
//            Array(shape) { x ->
//                Array(shape) { y ->
//                    TileState(position = Pair(x, y))
//                }
//            }
//        }
//        val sudokuBoardFilled = Array(9) { x ->
//            Array(9) { y ->
//                TileState(
//                    text = x.toString(),
//                    position = Pair(x,y)
//                )
//            }
//        }
//    }

}