package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.*
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState.Companion.emptySudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.ScannerState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.AndroidSudokuSolverTheme

@Composable
fun SudokuSolverScreen(
    modifier: Modifier = Modifier,
    cameraState: ScannerState = ScannerState.OFF,
    dimens: Int,
    solved: Boolean = false,
    selected: Pair<Int,Int>? = null,
    board: Array<Array<TileState>>,
    updateSelectionPosition: (Pair<Int, Int>) -> Unit
) {

    BoxWithConstraints(Modifier.fillMaxSize()) {

        val dims = calculateBoardDimensions()
        val tiles = dims.calculateTileDimensions()

        if (cameraState != ScannerState.OFF) {
            Camera(
                size = calculatePx(),
                tileList = tiles
            ) {
//                    vm.enterNewValue(
//                        newValue = it.text,
//                        position = it.position
//                    )
            }
        }

        SudokuBoard(
            modifier = modifier,
            board = board,
            borderColor = solved.bordColor(),
            selectedPosition = selected?.let { (x,y) -> Triple(x,y, dimens) }
        ) { position ->
            updateSelectionPosition(position)
        }

    }

}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SudokuScreenPreview() {


}