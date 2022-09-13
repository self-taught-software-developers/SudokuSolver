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
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.AndroidSudokuSolverTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.ExtendedTheme

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
                .padding(top = ExtendedTheme.padding.small),
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

//TODO move to alpha
private const val DividerAlpha = 0.12f

@Preview
@Composable
fun DefaultBottomBarPreview() {
    AndroidSudokuSolverTheme {
        DefaultBottomBar(
            onClickSolve = { }
        ) { }
    }
}