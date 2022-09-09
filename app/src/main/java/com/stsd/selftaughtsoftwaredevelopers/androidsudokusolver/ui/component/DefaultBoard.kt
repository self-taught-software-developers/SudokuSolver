package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import android.Manifest
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.toTileText
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BoardTile(
    modifier: Modifier= Modifier,
    value: Int,
    color: Color,
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
                textAlign = TextAlign.Center,
                style = MaterialTheme
                    .typography
                    .subtitle1
            )
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun BoxWithConstraintsScope.SudokuBoard(
    modifier: Modifier = Modifier,
    cameraEnabled: Boolean = false,
    board: List<Array<TileState>>,
    tiles: List<TileState>,
    position: Pair<Int, Int>?,
    vector: Int,
    boardColor: Color = CustomTheme.colors.primary,
    updateSelectedPositionWith: (Pair<Int, Int>) -> Unit
) {

    if (cameraEnabled) {
        CameraPermissionRequest {

                Camera(
                    modifier = modifier,
                    tiles = tiles
                ) {
    //                    vm.enterNewValue(
    //                        newValue = it.text,
    //                        position = it.position
    //                    )
                }
            Column(
                modifier = modifier
                    .defaultBorder(
                        borderColor = boardColor,
                        borderShape = CustomTheme.shapes.medium,
                        borderWidth = CustomTheme.padding.small_x2
                    )
                    .drawSudokuGrid(color = boardColor, vector = vector)
            ) {

                placeTiles(
                    board = board,
                    tileColor = boardColor,
                    selectedTile = position?.let {
                        Triple(
                            it.first,
                            it.second,
                            vector.multiplier()
                        )
                    }
                ) { updateSelectedPositionWith(it) }

            }

            }
    } else {

        Column(
            modifier = modifier
                .defaultBorder(
                    borderColor = boardColor,
                    borderShape = CustomTheme.shapes.medium,
                    borderWidth = CustomTheme.padding.small_x2
                )
                .drawSudokuGrid(color = boardColor, vector = vector)
        ) {

            placeTiles(
                board = board,
                tileColor = boardColor,
                selectedTile = position?.let {
                    Triple(
                        it.first,
                        it.second,
                        vector.multiplier()
                    )
                }
            ) { updateSelectedPositionWith(it) }

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