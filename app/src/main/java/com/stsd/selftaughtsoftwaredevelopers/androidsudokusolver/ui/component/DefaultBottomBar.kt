package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.background
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
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme

@Composable
fun DefaultBottomBar(
    onSolveBoard: () -> Unit,
    onEnterValue: (String) -> Unit
) {

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(CustomTheme.colors.primary)
            .padding(top = CustomTheme.padding.small),
        mainAxisAlignment = MainAxisAlignment.Center
    ) {
        (1..9).forEach { number ->
            DefaultNumericButton(
                num = number,
                onClick = onEnterValue
            )
        }

        DefaultIconButton(
            imageVector = Icons.Default.SelectAll,
            backgroundColor = CustomTheme.colors.secondary
        ) { onSolveBoard() }
    }

}

@Preview
@Composable
fun DefaultBottomBarPreview() {
    AndroidSudokuSolverTheme {
        DefaultBottomBar(
            onSolveBoard = { }
        ) { }
    }
}