package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.icons.rounded.BorderClear
import androidx.compose.material.icons.rounded.PublishedWithChanges
import androidx.compose.material.icons.rounded.Replay
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.R
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
                imageVector = rounded.Replay,
                text = stringResource(id = R.string.BUTTON_undo)
            ) { onUndoRecentChange() }

            IconButton(
                imageVector = rounded.BorderClear,
                text = stringResource(id = R.string.BUTTON_erase)
            ) { onClearBoard() }

            IconButton(
                backgroundColor = CustomTheme.colors.onSurface.copy(alpha = 0.05F),
                imageVector = rounded.PublishedWithChanges,
                text = stringResource(id = R.string.BUTTON_solve)
            ) { onSolveBoard() }


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