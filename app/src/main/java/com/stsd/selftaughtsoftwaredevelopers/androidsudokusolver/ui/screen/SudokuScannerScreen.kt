package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.Camera
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.calculateBoardDimensions
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.calculateTileDimensions
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.viewmodel.SudokuViewModel

@Composable
fun SudokuScannerScreen(vm: SudokuViewModel = viewModel()) {

    val state by vm.sudokuBoardStateAlt.collectAsState()

    val solved by state.solved.collectAsState()
    val board by state.initialBoard.collectAsState()
    val selected by state.selectedPosition.collectAsState()

    BoxWithConstraints(Modifier.fillMaxSize()) {

        val dims = calculateBoardDimensions()
        val tiles = dims.calculateTileDimensions()

        Camera(tiles = tiles)

//        SudokuBoard(
//            board = board,
//            borderColor = solved.bordColor(),
//            selectedPosition = selected?.let { (x,y) -> Triple(x,y, state.dimensions.third) }
//        ) { newPosition ->
//            vm.updateSelectedPosition(newPosition)
//        }
        Canvas(modifier = Modifier.fillMaxSize()) {

            drawRect(
                size = dims.size,
                topLeft = dims.topLeft,
                color = Color.Red,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 12f)
            )

            tiles.forEach {
                drawRect(
                    size = it.size,
                    topLeft = it.topLeft,
                    color = Color.White,
                    style = androidx.compose.ui.graphics.drawscope.Stroke(width = 5f)
                )
            }

        }
    }

}