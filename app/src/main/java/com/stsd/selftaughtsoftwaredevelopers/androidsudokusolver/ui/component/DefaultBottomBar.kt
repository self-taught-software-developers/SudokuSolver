package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SelectAll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

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
            modifier = modifier
                .fillMaxWidth()
                .background(ExtendedTheme.colors.background)
                .padding(top = ExtendedTheme.spacing.small),
            mainAxisAlignment = MainAxisAlignment.Center
        ) {
            repeat(9) {
                ThemedNumericButton(
                    enabled = enabled,
                    numericValue = (it + 1),
                    onClick = onEnterValue
                )
            }

            ThemedIconButton(
                enabled = enabled,
                imageVector = Icons.Default.SelectAll,
                backgroundColor = ExtendedTheme.colors.primary,
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