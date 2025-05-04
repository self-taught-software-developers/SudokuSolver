package com.stsd.selftaughtsoftwaredevelopers.shared.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Redo
import androidx.compose.material.icons.automirrored.filled.Undo
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Redo
import androidx.compose.material.icons.rounded.PlayCircleOutline
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cerve.development.ui.component.CerveButton
import com.cerve.development.ui.component.CerveHorizontalDivider
import com.cerve.development.ui.component.CerveIcon
import com.cerve.development.ui.component.CerveIconButton
import com.cerve.development.ui.component.theme.ExtendedTheme.colors
import com.cerve.development.ui.component.theme.ExtendedTheme.dimensions
import com.stsd.selftaughtsoftwaredevelopers.resources.Res
import com.stsd.selftaughtsoftwaredevelopers.resources.app_name
import com.stsd.selftaughtsoftwaredevelopers.resources.solve
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.component.icon.rounded
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.TileValue
import org.jetbrains.compose.resources.stringResource

@Composable
fun SudokuBottomBar(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onUndoLastClick: () -> Unit = { },
    onRedoLastClick: () -> Unit = { },
    onUndoAllClick: () -> Unit = { },
    onSolveClick: () -> Unit = { },
    onEnterValue: (String) -> Unit = { }
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(BottomAppBarDefaults.windowInsets),
        verticalArrangement = Arrangement.spacedBy(dimensions.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CerveHorizontalDivider()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                space = dimensions.medium,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            CerveIconButton(
                containerColor = colors.secondaryContainer,
                imageVector = Icons.AutoMirrored.Default.Undo
            ) { onUndoLastClick() }

            CerveIconButton(
                containerColor = colors.secondaryContainer,
                imageVector = Icons.AutoMirrored.Default.Redo
            ) { onRedoLastClick() }

            CerveIconButton(
                containerColor = colors.secondaryContainer,
                imageVector = Icons.Default.Delete
            ) { onUndoAllClick() }
        }

        FlowRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(
                space = dimensions.medium,
                alignment = Alignment.CenterHorizontally
            ),
        ) {
            TileValue.entries.forEach { tile ->
                CerveButton(
                    text = tile.value,
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
            CerveButton(
                enabled = enabled,
                text = stringResource(Res.string.solve)
            ) { onSolveClick() }
        }
    }


}
