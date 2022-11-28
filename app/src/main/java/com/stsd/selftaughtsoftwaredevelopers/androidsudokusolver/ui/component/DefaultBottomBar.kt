package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.rounded.BorderClear
import androidx.compose.material.icons.rounded.PublishedWithChanges
import androidx.compose.material.icons.rounded.Replay
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.cerve.co.material3extension.designsystem.ExtendedTheme.spacing
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.R

@Composable
fun DefaultBottomBar(
    onUndoRecentChange: () -> Unit,
    onClearBoard: () -> Unit,
    onSolveBoard: () -> Unit,
    onEnterNewValue: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        TwoRowsOfButtonsOffset { number ->
            onEnterNewValue(number)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = spacing.small),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                imageVector = rounded.Replay,
                text = stringResource(id = R.string.BUTTON_undo)
            ) { onUndoRecentChange() }

            IconButton(
                imageVector = rounded.BorderClear,
                text = stringResource(id = R.string.BUTTON_erase)
            ) { onClearBoard() }

            IconButton(
                backgroundColor = ExtendedTheme.colors.onSurface.copy(alpha = 0.05F),
                imageVector = rounded.PublishedWithChanges,
                text = stringResource(id = R.string.BUTTON_solve)
            ) { onSolveBoard() }
        }
    }
}

// preview
@Preview
@Composable
fun DefaultBottomBarPreview() {
    DefaultBottomBar(
        onUndoRecentChange = { },
        onClearBoard = { },
        onSolveBoard = { },
        onEnterNewValue = { }
    )
}
