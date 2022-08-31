package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import androidx.compose.runtime.Composable

@Composable
fun SudokuScannerScreen() {

//    val state by vm.sudokuBoardStateAlt.collectAsState()
//
//    val solved by state.solved.collectAsState()
//    val board by state.initialBoard.collectAsState()
//    val selected by state.selectedPosition.collectAsState()
//
//    BoxWithConstraints(Modifier.fillMaxSize()) {
//
//        val dims = calculateBoardDimensions()
//        val cells = dims.calculateTileDimensions()
//
//
//        Camera(
//            size = calculatePx(),
//            cellList = cells
//        ) {
//            vm.enterNewValue(
//                newValue = it.text,
//                position = it.position
//            )
//        }
//
//        SudokuBoard(
//            board = board,
//            borderColor = solved.bordColor(),
//            selectedPosition = selected?.let { (x,y) -> Triple(x,y, state.dimensions.third) }
//        ) { newPosition ->
//            vm.updateSelectedPosition(newPosition)
//        }
////        Canvas(modifier = Modifier.fillMaxSize()) {
////
////            drawRect(
////                size = dims.size,
////                topLeft = dims.topLeft,
////                color = Color.Red,
////                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 12f)
////            )
////
////            cells.forEach {
////                drawRect(
////                    size = it.rect.size,
////                    topLeft = it.rect.topLeft,
////                    color = Color.White,
////                    style = androidx.compose.ui.graphics.drawscope.Stroke(width = 5f)
////                )
////            }
////
////        }
//    }

}