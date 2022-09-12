package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.colors
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.padding
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.sizing

@Composable
fun DefaultTopBar(
    modifier: Modifier = Modifier,
    placementSpeed: TimeState,
    updateSelection: (TimeState) -> Unit
) {


    Row(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(sizing.default_top_bar)
            .background(colors.primary)
            .padding(horizontal = padding.small),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        TimeState.values().onEach { state ->
            DefaultIconButton(
                imageVector = state.icon,
                enabled = placementSpeed != state
            ) { updateSelection(state) }
        }

    }

}