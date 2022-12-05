package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.cerve.co.material3extension.designsystem.ExtendedTheme.spacing
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.toTileText
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.AllPreviews

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

@Composable
fun SudokuBoard(
    modifier: Modifier = Modifier,
    board: List<TileState>,
    position: Pair<Int, Int>?,
    vector: Int,
    boardColor: Color = ExtendedTheme.colors.primary,
    updateSelectedPositionWith: (Pair<Int, Int>) -> Unit
) {

    val selected = remember(position) {
        derivedStateOf { board.firstOrNull { position == it.position } }
    }

    Column(
        modifier = modifier
            .padding(horizontal = spacing.medium)
            .defaultBorder(
                borderColor = boardColor,
                borderShape = ExtendedTheme.shapes.medium,
                borderWidth = spacing.small
            )
            .drawSudokuGrid(color = boardColor, vector = vector)
    ) {

        placeTiles(
            board = board,
            tileColor = boardColor,
            selectedTile = selected.value
        ) { updateSelectedPositionWith(it) }

    }

}


@AllPreviews
@Composable
fun SudokuBoardPreview() {
//
//    AndroidSudokuSolverTheme {
//        SudokuBoard(
//            board = List(9) { x ->
//                List(9) { y ->
//                    TileState(position = Pair(x, y))
//                }
//            },
//            position = Pair(0, 0),
//            vector = 9
//        ) {
//
//        }
//    }

}