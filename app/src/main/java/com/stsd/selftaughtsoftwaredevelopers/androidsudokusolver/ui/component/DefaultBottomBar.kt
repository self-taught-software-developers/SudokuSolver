package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.rounded.ClearAll
import androidx.compose.material.icons.rounded.ForwardToInbox
import androidx.compose.material.icons.rounded.PlayCircleOutline
import androidx.compose.material.icons.rounded.Undo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.co.material3extension.designsystem.ExtendedTheme.spacing
import com.cerve.co.material3extension.designsystem.rounded
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.IconItem
import kotlinx.collections.immutable.persistentListOf

@Composable
fun DefaultBottomBar(
    color: @Composable () -> Color,
    onFeatureRequest: () -> Unit,
    onUndoLastClick: () -> Unit,
    onUndoAllClick: () -> Unit,
    onClickSolve: () -> Unit,
    onEnterValue: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Column {

        ThemedFab(
            modifier = Modifier
                .padding(spacing.small)
                .align(Alignment.CenterHorizontally),
            enabled = enabled,
            color = { color() },
            items = {
                persistentListOf(
                    IconItem(
                        icon = rounded.ForwardToInbox,
                        onClick = onFeatureRequest
                    ),
                    IconItem(
                        icon = rounded.Undo,
                        onClick = onUndoLastClick
                    ),
                    IconItem(
                        icon = rounded.ClearAll,
                        onClick = onUndoAllClick
                    )
                )
            }
        )

        ThemedDivider(color = color())

        FlowRow(
            modifier = modifier.padding(vertical = spacing.small),
            mainAxisSize = SizeMode.Expand,
            mainAxisAlignment = FlowMainAxisAlignment.Center,
            crossAxisAlignment = FlowCrossAxisAlignment.Center
        ) {
            repeat(9) {
                ThemedNumericButton(
                    modifier = Modifier.padding(spacing.small),
                    enabled = enabled,
                    backgroundColor = color(),
                    numericValue = (it + 1),
                    onClick = onEnterValue
                )
            }

            ThemedIconButton(
                modifier = Modifier.padding(spacing.small),
                enabled = enabled,
                backgroundColor = color(),
                imageVector = rounded.PlayCircleOutline,
                onClick = onClickSolve
            )
        }
    }
}

@Preview
@Composable
fun DefaultBottomBarPreview() {
    DefaultBottomBar(
        onUndoAllClick = { },
        onUndoLastClick = { },
        onFeatureRequest = { },
        onClickSolve = { },
        onEnterValue = { },
        color = { Color.Green }
    )
}
