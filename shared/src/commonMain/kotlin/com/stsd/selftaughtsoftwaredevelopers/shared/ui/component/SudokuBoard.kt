package com.stsd.selftaughtsoftwaredevelopers.shared.ui.component

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cerve.development.ui.component.theme.ExtendedTheme
import com.cerve.development.ui.component.theme.ExtendedTheme.colors
import com.cerve.development.ui.component.theme.ExtendedTheme.dimensions
import com.cerve.development.ui.component.theme.ExtendedTheme.shapes
import com.cerve.development.ui.component.theme.ExtendedTheme.sizes
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.drawSudokuGrid
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.placeTiles
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.Position
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.TileState
import kotlin.math.sqrt

@Composable
fun SudokuBoard(
    board: List<TileState>,
    color: @Composable () -> Color,
    modifier: Modifier = Modifier,
    selectedPosition: Position? = null,
    onPositionUpdate: (Position) -> Unit = { }
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
            .padding(dimensions.medium),
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
                    color = color(),
                    vector = vector
                )
                .defaultBorder(
                    borderColor = color(),
                    borderShape = shapes.medium,
                    borderWidth = ExtendedTheme.dimensions.thin
                )
        ) {
            placeTiles(
                board = board,
                tileColor = colors.secondary.copy(alpha = 0.2f),
                tileSize = tileSize,
                selectedTile = selected.value
            ) { onPositionUpdate(it) }
        }
    }
}
