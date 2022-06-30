package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.LocalPadding

@Composable
fun DefaultBottomBar(
    onUndoRecentChange: () -> Unit,
    onClearBoard: () -> Unit,
    onSolveBoard: () -> Unit,
    onEnterNewValue: (String) -> Unit
) {

    val padding = LocalPadding.current

    Column {

        TwoRowsOfButtonsOffset { number ->
            onEnterNewValue(number)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = padding.small),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                imageVector = Icons.Default.Replay,
                text = "Undo"
            ) { onUndoRecentChange() }

            IconButton(
                imageVector = Icons.Default.BorderClear,
                text = "Erase"
            ) { onClearBoard() }

            Surface(
                color = CustomTheme.colors.onSurface.copy(alpha = 0.05F),
                shape = RoundedCornerShape(10.dp)
            ) {
                IconButton(
                    imageVector = Icons.Default.PublishedWithChanges,
                    text = "Solve"
                ) { onSolveBoard() }
            }

        }
    }



}


@Preview
@Composable
fun DefaultBottomBarPreview() {
    DefaultBottomBar(
        onUndoRecentChange = { },
        onClearBoard = { },
        onSolveBoard = { }
    ) {

    }
}