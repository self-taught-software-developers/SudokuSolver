package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.*
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.IconItem
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.DarkPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun SudokuSolverScreen(
    modifier: Modifier = Modifier,
    solutionSpeedState: Flow<TimeState>,
) {

    var enabled by rememberSaveable { mutableStateOf(true) }
    var isCameraOn by rememberSaveable { mutableStateOf(false) }
    var showMoreItems by remember { mutableStateOf<List<IconItem>?>(null) }

    //TODO INVESTIGATE WHETHER OR NOT THIS IS GOOD PRACTICE.
    val speed by solutionSpeedState.collectAsState(TimeState.DEFAULT_SPEED)

    //TODO WHILE SOLVING DISABLE
    Scaffold(
        modifier = modifier,
        topBar = {
            DefaultTopBar(
                enabled = enabled,
                timeState = TimeState.DEFAULT_SPEED,
                isCameraOn = isCameraOn,
                toggleCamera = { isCameraOn = !isCameraOn }
            ) {
                showMoreItems = showMoreItems?.let { null } ?: listOf(
                    IconItem(rounded.Timer3) { /*TODO modify how quickly a solution is generated*/ },
                    IconItem(rounded.Timer10) { /*TODO modify how quickly a solution is generated*/ },
                    IconItem(rounded.Timer) { /*TODO modify how quickly a solution is generated*/ },
                    IconItem(rounded.Snooze) { /*TODO modify how quickly a solution is generated*/ }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            DefaultFab(
                enabled = enabled,
                items = listOf(
                    IconItem(rounded.Undo) { /*TODO clear the recently placed value on the sudoku board*/ },
                    IconItem(rounded.ClearAll) { /*TODO clear all values on the sudoku board*/ }
                )
            )
        },
        bottomBar = {
             DefaultBottomBar(
                 enabled = enabled,
                 onClickSolve = { /*TODO solve the sudoku board onClick*/ }
            ) { /*TODO onClick put value as selected tile value*/ }
        },
    ) { bounds ->

        MoreOptionsBar(iconList = showMoreItems) { showMoreItems = null }


        //TODO PLACE THE CAMERA AND SUDOKU GRID HERE

    }

//    val solved by state.solved.collectAsState()
//    val board by state.initialBoard.collectAsState()
//    val selected by state.selectedPosition.collectAsState()
//
//    BoxWithConstraints(Modifier.fillMaxSize()) {
//        val vector = state.dimensions.vector()
//        val tiles = calculateTileDimensions(cellCount = vector)
//
//        /*
//            Dimensions (2x2 / 3x3 / 4x4)
//            This will allow us to draw the grid with dynamic dimensions.
//            Vectors -> The dimensions to the 2nd power will give us the number of rows and columns
//            Tiles -> Then the 2nd power to get the number of tiles
//            Row -> Then chunked(grid.power) can be used to split tiles in a board like way.
//            listOfTileStates
//            This will be used to identify in which tile an analyzed number falls within.
//         */
//
////        if (cameraState != ScannerState.OFF) {
////            Camera(
////                size = calculatePx(),
////                tileList = calculateTileDimensions(state.area())
////            ) {
//////                    vm.enterNewValue(
//////                        newValue = it.text,
//////                        position = it.position
//////                    )
////            }
////        }
//
//        SudokuBoard(
//            modifier = modifier,
//            vector = vector,
//            board = tiles,
//            borderColor = solved.bordColor(),
//            selectedPosition = selected?.let { (x,y) -> Triple(x,y, state.dimensions.multiplier) }
//        ) { position ->
//            updateSelectionPosition(position)
//        }
//
//    }

}

@DarkPreview
@Composable
fun SudokuSolverScreenPreview() {
    SudokuSolverScreen(solutionSpeedState = flowOf(TimeState.DEFAULT_SPEED))
}