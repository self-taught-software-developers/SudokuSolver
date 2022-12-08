package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.R
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    placementSpeed: TimeState,
    onSelectionUpdate: (TimeState) -> Unit,
    modifier: Modifier = Modifier,
    color: @Composable () -> Color = { colors.primary }
) {
    Column(modifier = modifier) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            actions = {
                TimeState.values().forEach { state ->

                    ThemedIconButton(
                        icon = state.icon,
                        backgroundColor = color(),
                        enabled = { placementSpeed != state }
                    ) { onSelectionUpdate(state) }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            )
        )

        ThemedDivider(color = color())
    }
}
