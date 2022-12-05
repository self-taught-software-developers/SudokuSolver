package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.cerve.co.material3extension.designsystem.ExtendedTheme.spacing
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState

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
                .background(colors.background)
                .padding(horizontal = spacing.small),
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