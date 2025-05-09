package com.stsd.selftaughtsoftwaredevelopers.shared.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cerve.development.ui.component.CerveButton
import com.cerve.development.ui.component.CerveHorizontalDivider
import com.cerve.development.ui.component.CerveIconButton
import com.cerve.development.ui.component.theme.ExtendedTheme.colors
import com.cerve.development.ui.component.theme.ExtendedTheme.dimensions
import com.stsd.selftaughtsoftwaredevelopers.resources.Res
import com.stsd.selftaughtsoftwaredevelopers.resources.solve
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.TileValue
import org.jetbrains.compose.resources.stringResource

@Composable
fun SudokuBottomBar(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onResetClick: () -> Unit = { },
    onDeleteClick: () -> Unit = { },
    onSolveClick: () -> Unit = { },
    onEnterValue: (Int) -> Unit = { }
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(BottomAppBarDefaults.windowInsets),
        verticalArrangement = Arrangement.spacedBy(
            space = dimensions.medium,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CerveHorizontalDivider()

        FlowRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(
                space = dimensions.medium,
                alignment = Alignment.CenterHorizontally
            ),
        ) {
            TileValue.entries.forEach { tile ->
                CerveButton(
                    enabled = enabled,
                    text = tile.value.toString(),
                ) { onEnterValue(tile.value) }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                space = dimensions.medium,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            CerveIconButton(
                enabled = enabled,
                containerColor = colors.secondaryContainer,
                imageVector = Icons.Default.RestartAlt
            ) { onResetClick() }

            CerveIconButton(
                enabled = enabled,
                containerColor = colors.secondaryContainer,
                imageVector = Icons.Default.Delete
            ) { onDeleteClick() }

            CerveButton(
                enabled = enabled,
                text = stringResource(Res.string.solve)
            ) { onSolveClick() }
        }
    }

}
