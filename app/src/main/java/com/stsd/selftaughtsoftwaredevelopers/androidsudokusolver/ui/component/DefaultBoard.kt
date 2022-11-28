package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.co.material3extension.designsystem.ExtendedTheme.spacing
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState.Companion.emptySudokuBoard
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState.Companion.sudokuBoardFilled
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.toTileText
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.AndroidSudokuSolverTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BoardTile(
    value: Int,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    onClick: () -> Unit = { }
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
        ) {
            Text(text = toTileText(value))
        }
    }
}

@Composable
fun SudokuBoard(
    board: Array<Array<TileState>>,
    borderColor: Color,
    modifier: Modifier = Modifier,
    selectedPosition: Triple<Int, Int, Int>? = null,
    onPositionSelected: (Pair<Int, Int>) -> Unit = { }
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = spacing.medium),
        contentAlignment = Alignment.Center
    ) {
        val tileSize = (minOf(maxWidth, maxHeight) / 9)

        Column(
            modifier = Modifier
                .defaultBorder(borderColor)
                .drawSudokuGrid(borderColor)
        ) {
            board.forEachIndexed { rowIndex, rowOfTiles ->
                placeTiles(
                    tileSize = tileSize,
                    sudokuRow = Pair(rowIndex, rowOfTiles),
                    selectedTilePosition = selectedPosition
                ) { onPositionSelected(Pair(it.first, it.second)) }
            }
        }
    }
}

@Preview
@Composable
fun SudokuBoardPreview() {
    AndroidSudokuSolverTheme {
        val board by remember { mutableStateOf(emptySudokuBoard(Triple(9, 9, 3))) }

        SudokuBoard(
            modifier = Modifier,
            board = board,
            borderColor = null.bordColor()
        ) {
        }
    }
}

@Preview
@Composable
fun FillSudokuBoardPreview() {
    AndroidSudokuSolverTheme {
        val board by remember { mutableStateOf(sudokuBoardFilled) }

        SudokuBoard(
            modifier = Modifier,
            board = board,
            borderColor = null.bordColor()
        ) {
        }
    }
}

@Preview
@Composable
fun BoardTilePreview() {
    AndroidSudokuSolverTheme {
        BoardTile(
            modifier = Modifier,
            value = 1
        ) {
        }
    }
}
