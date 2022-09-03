package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.calculateBoardDimensions
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.calculatePx
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.EMPTY_TILE
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.chunked
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.copy
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.takeTopOrNull
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.top
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
    var board = mutableStateListOf<Array<TileState>>()

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

    private fun toBoardLayout(): List<Array<TileState>> = tiles.chunked(vector)

    private val _solved = MutableStateFlow<Boolean?>(null)
    val solved : StateFlow<Boolean?> = _solved.asStateFlow()

    private val _selectedPosition = MutableStateFlow<Pair<Int,Int>?>(null)
    var selectedPosition : StateFlow<Pair<Int, Int>?> = _selectedPosition.asStateFlow()




    private var backStack : MutableList<Pair<Int, Int>?> = mutableListOf()

//    fun updateSolutionState(value: Boolean? = null) {
//        _solved.update { value }
//    }
    fun updateSelected(position: Pair<Int, Int>) {
        _selectedPosition.update { position }
    }
    fun changeValue(value: String) {
        _selectedPosition.value?.let { (x, y) ->

            board[x] = board[x].copy { it[y].text = value }

            if (value.isEmpty()) {
                _selectedPosition.update {
                    backStack.takeTopOrNull((_selectedPosition.value))
                }
            } else {
                backStack.top(_selectedPosition.value)
            }

        }
    }
//    fun changeValue(
//        value: String,
//        position: Pair<Int, Int>
//    ) {
//
//        selectPosition(position)
//        changeValue(value)
//
//    }
    fun undoLast() = changeValue(EMPTY_TILE)
    fun clearBoard() {
//        if (solved.value != null) updateSolutionState()

        (0 until vector).forEach { point ->
            board[point] = board[point].copy { x ->
                x.map { it.text = EMPTY_TILE }
            }
        }

        _selectedPosition.update { null }.also { backStack.clear() }
    }
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

}