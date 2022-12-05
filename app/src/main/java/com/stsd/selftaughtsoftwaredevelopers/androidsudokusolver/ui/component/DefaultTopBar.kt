package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.R
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    placementSpeed: TimeState,
    onSelectionUpdate: (TimeState) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column {
        TopAppBar(
            modifier = modifier,
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            actions = {
                TimeState.values().forEach { state ->

                    ThemedIconButton(
                        icon = state.icon,
                        enabled = placementSpeed != state
                    ) { onSelectionUpdate(state) }

                }
            }
        )

        ThemedDivider()
    }
}