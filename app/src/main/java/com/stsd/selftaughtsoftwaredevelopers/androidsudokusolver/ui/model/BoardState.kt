package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import android.service.quicksettings.Tile
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.calculateBoardDimensions
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.calculatePx
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.EMPTY_TILE
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.*
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

            board[x] = isPlacementValid(
                value = value,
                position = Pair(x,y),
                board = board.toTypedArray().clone()
            ).clone()


//            board[x] = board[x].copy {
//                it[y].text = value
////                it[y].isValid = isPlacementValid()
//            }

//            board = board.validatedCopy {
//                this.placeAndValidate { this[x][y].text = value }
//            }
//
//            isPlacementValid()

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

    private fun isPlacementValid(
        value: String,
        position: Pair<Int, Int>,
        board: Array<Array<TileState>>
    ) : Array<TileState> {

        position.let { (x,y) ->

//            val area = sqrt(board.size.toDouble()).toInt()
            board.apply {
                this[x][y].text = value
                this[x].filter { it.text.isNotEmpty() }.valid()

//                this.map { it[y] }.filter { it.text.isNotEmpty() }
//                this.flatMap {
//                    it.filter { tile ->
//                        tile.position.let { (tx, ty) ->
//                            Pair(x/area, y/area) == Pair(tx/area, ty/area)
//                        }
//                    }
//                }.filter { it.text.isNotEmpty() }

                return this[x]
            }

//            board.apply {

//            }

//            board[x].filter { it.text.isNotEmpty() }.plane { it.text } &&
//            board.map { it[y] }.filter { it.text.isNotEmpty() }.plane { it.text } &&
//            board.flatMap {
//                it.filter { tile ->
//                    tile.position.let { (tx, ty) ->
//                        Pair(x/area, y/area) == Pair(tx/area, ty/area)
//                    }
//                }
//            }.filter { it.text.isNotEmpty() }.plane { it.text }

        }

    }

    private fun List<TileState>.valid() {

        val occurrenceCount = this.groupingBy { it.text }.eachCount()

        this.forEach {
            it.isValid = !occurrenceCount[it.text].greaterThanOne()
        }

    }

}