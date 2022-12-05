package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.rounded.PlayCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.co.material3extension.designsystem.ExtendedTheme.spacing
import com.cerve.co.material3extension.designsystem.rounded
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun DefaultBottomBar(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClickSolve: () -> Unit,
    onEnterValue: (String) -> Unit
) {

    Column {

        ThemedDivider()

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
                    numericValue = (it + 1),
                    onClick = onEnterValue
                )
            }

            ThemedIconButton(
                modifier = Modifier.padding(spacing.small),
                enabled = enabled,
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
        onClickSolve = { }
    ) { }

}