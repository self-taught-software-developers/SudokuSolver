package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.cerve.co.material3extension.designsystem.ExtendedTheme.shapes
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.cerve.co.material3extension.designsystem.ExtendedTheme.spacing
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.toTileText
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.AllPreviews
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlin.math.sqrt

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
    board: PersistentList<TileState>,
    modifier: Modifier = Modifier,
    selectedPosition: Pair<Int, Int>? = null,
    boardColor: Color = ExtendedTheme.colors.primary,
    onPositionUpdate: (Pair<Int, Int>) -> Unit = { }
) {

    val selected = remember(selectedPosition) {
        derivedStateOf { board.firstOrNull { selectedPosition == it.position } }
    }

    val vector by remember(board.size) {
        mutableStateOf(sqrt(board.size.toFloat()).toInt())
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .padding(spacing.medium),
        contentAlignment = Alignment.Center
    ) {

        val dimensions = minOf(maxWidth, maxHeight)
        val tileSize by remember(dimensions) {
            mutableStateOf(dimensions / vector)
        }

        Column(
            modifier = Modifier
                .size(dimensions)
                .drawSudokuGrid(
                    color = boardColor,
                    vector = vector
                )
                .defaultBorder(
                    borderColor = boardColor,
                    borderShape = shapes.medium,
                    borderWidth = sizes.xSmall
                )
        ) {

            placeTiles(
                board = board,
                tileColor = boardColor,
                tileSize = tileSize,
                selectedTile = selected.value
            ) { onPositionUpdate(it) }

        }

    }

}

@AllPreviews
@Composable
fun SudokuBoardPreview() {

    SudokuBoard(
        board = persistentListOf(),
        selectedPosition = Pair(0, 0)
    ) {

    }

}