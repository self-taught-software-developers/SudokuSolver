package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.ExtendedTheme.colors
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.ExtendedTheme.dims
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.ExtendedTheme.padding

@Composable
fun DefaultTopBar(
    modifier: Modifier = Modifier,
    placementSpeed: TimeState,
    updateSelection: (TimeState) -> Unit
) {

    Column {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .requiredHeight(dims.default_top_bar)
                .background(colors.background)
                .padding(horizontal = padding.small),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            TimeState.values().forEach { state ->

                ThemedIconButton(
                    icon = state.icon,
                    enabled = placementSpeed != state
                ) { updateSelection(state) }

            }

        }
        ThemedDivider()
    }
}