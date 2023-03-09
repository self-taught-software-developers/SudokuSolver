package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
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

    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            LandscapeTopBar(
                placementSpeed = placementSpeed,
                onSelectionUpdate = onSelectionUpdate,
                modifier =  modifier,
                color = color
            )
        }
        else -> {

            PortraitTopBar(
                placementSpeed = placementSpeed,
                onSelectionUpdate = onSelectionUpdate,
                modifier = modifier,
                color = color
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortraitTopBar(
    placementSpeed: TimeState,
    onSelectionUpdate: (TimeState) -> Unit,
    modifier: Modifier = Modifier,
    color: @Composable () -> Color = { colors.primary }
) {
    Column(modifier = modifier) {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, actions = {
            TimeState.values().forEach { state ->

                ThemedIconButton(icon = state.icon,
                    border = themedBorder(color = color()),
                    backgroundColor = color(),
                    enabled = { placementSpeed != state }) { onSelectionUpdate(state) }
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
        )
        ThemedDivider(color = color())
    }
}


@Composable
fun LandscapeTopBar(
    placementSpeed: TimeState,
    onSelectionUpdate: (TimeState) -> Unit,
    modifier: Modifier = Modifier,
    color: @Composable () -> Color = { colors.primary }
) {

    Row(modifier =  modifier.wrapContentWidth(), verticalAlignment = Alignment.CenterVertically) {
        Column(
            modifier = Modifier.fillMaxHeight().padding(sizes.medium),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TimeState.values().forEach { state ->
                    ThemedIconButton(icon = state.icon,
                        border = themedBorder(color = color()),
                        backgroundColor = color(),
                        enabled = { placementSpeed != state }) { onSelectionUpdate(state) }
                }

            }
            Text(text = stringResource(id = R.string.app_name))
//        ThemedVerticalDivider(color = color())
        }
    }
}
