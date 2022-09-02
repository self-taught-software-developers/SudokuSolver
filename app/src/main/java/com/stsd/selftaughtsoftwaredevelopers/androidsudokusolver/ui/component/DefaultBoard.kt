package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.toTileText

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BoardTile(
    modifier: Modifier= Modifier,
    value: Int,
    color: Color = Color.Unspecified,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .drawWithCache {
                onDrawBehind {
                    drawRect(color)
                }
            },
        contentAlignment = Alignment.Center
    ) {

        AnimatedContent(
            targetState = value,
            transitionSpec = {
                // Compare the incoming number with the previous number.
                if (targetState > initialState) {
                    // If the target number is larger, it slides up and fades in
                    // while the initial (smaller) number slides up and fades out.
                    slideInVertically { height -> height } + fadeIn() with
                            slideOutVertically { height -> -height } + fadeOut()
                } else {
                    // If the target number is smaller, it slides down and fades in
                    // while the initial number slides down and fades out.
                    slideInVertically { height -> -height } + fadeIn() with
                            slideOutVertically { height -> height } + fadeOut()
                }.using(
                    // Disable clipping since the faded slide-in/out should
                    // be displayed out of bounds.
                    SizeTransform(clip = false)
                )
            }
        ) { targetCount ->
            Text(
                text = toTileText(targetCount),

            )
        }
    }
}

@Composable
fun SudokuBoard(
    modifier: Modifier = Modifier,
    state: BoardState,
    cameraEnabled: Boolean = false,
    enabled: Boolean,
    onDisabled: (Boolean) -> Unit
) {

    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

//        /*
//            Dimensions (2x2 / 3x3 / 4x4)
//            This will allow us to draw the grid with dynamic dimensions.
//            Vectors -> The dimensions to the 2nd power will give us the number of rows and columns
//            Tiles -> Then the 2nd power to get the number of tiles
//            Row -> Then chunked(grid.power) can be used to split tiles in a board like way.
//            listOfTileStates
//            This will be used to identify in which tile an analyzed number falls within.
//         */


        if (cameraEnabled) {
            Camera(
                size = state.resolution,
                tiles = state.tiles
            ) {
//                    vm.enterNewValue(
//                        newValue = it.text,
//                        position = it.position
//                    )
            }
        }

        Column(
            modifier = Modifier
                .defaultBorder(Color.Black)
                .drawSudokuGrid(
                    color = Color.Black,
                    vector = state.vector
                )
        ) {

//            placeTiles(
//                vector = vector,
//                tiles = tiles,
//                selectedTilePosition = selectedPosition
//            ) { onPositionSelected(Pair(it.first, it.second)) }

        }
    }
}

@Preview
@Composable
fun SudokuBoardPreview() {

//    SudokuBoard(enabled = true) {
//
//    }

}