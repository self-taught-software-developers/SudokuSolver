package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.*
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.ScannerState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.AndroidSudokuSolverTheme

@Composable
fun SudokuSolverScreen(
    modifier: Modifier = Modifier,
    cameraState: ScannerState = ScannerState.OFF,
    state: BoardState,
    updateSelectionPosition: (Pair<Int, Int>) -> Unit
) {

    val solved by state.solved.collectAsState()
    val board by state.initialBoard.collectAsState()
    val selected by state.selectedPosition.collectAsState()

    BoxWithConstraints(Modifier.fillMaxSize()) {

        println(state.dimensions.vector())

        /*
            Dimensions (2x2 / 3x3 / 4x4)
            This will allow us to draw the grid with dynamic dimensions.
            Vectors -> The dimensions to the 2nd power will give us the number of rows and columns
            Tiles -> Then the 2nd power to get the number of tiles
            Row -> Then chunked(grid.power) can be used to split tiles in a board like way.
            listOfTileStates
            This will be used to identify in which tile an analyzed number falls within.
         */

//        if (cameraState != ScannerState.OFF) {
//            Camera(
//                size = calculatePx(),
//                tileList = calculateTileDimensions(state.area())
//            ) {
////                    vm.enterNewValue(
////                        newValue = it.text,
////                        position = it.position
////                    )
//            }
//        }

        SudokuBoard(
            modifier = modifier,
            board = board,
            tileSize = tileSize(),
            borderColor = solved.bordColor(),
            selectedPosition = selected?.let { (x,y) -> Triple(x,y, state.dimensions.multiplier) }
        ) { position ->
            updateSelectionPosition(position)
        }

    }

}

@Preview
@Composable
fun SudokuSolverScreenPreview() {

    AndroidSudokuSolverTheme {
        SudokuSolverScreen(
            state = BoardState()
        ) {

        }
    }
}