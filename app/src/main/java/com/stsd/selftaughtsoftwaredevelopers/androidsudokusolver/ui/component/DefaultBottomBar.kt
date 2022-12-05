package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.rounded.PlayCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.cerve.co.material3extension.designsystem.ExtendedTheme.spacing
import com.cerve.co.material3extension.designsystem.rounded
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun DefaultBottomBar(
    onClickSolve: () -> Unit,
    onEnterValue: (String) -> Unit,
    modifier: Modifier = Modifier,
    dividerColor: Color = colors.primary,
    enabled: Boolean = true
) {
    Column {
        ThemedDivider(color = dividerColor)

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
                    backgroundColor = dividerColor,
                    numericValue = (it + 1),
                    onClick = onEnterValue
                )
            }

            ThemedIconButton(
                modifier = Modifier.padding(spacing.small),
                enabled = enabled,
                backgroundColor = dividerColor,
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
        onClickSolve = { },
        onEnterValue = { }
    )
}
