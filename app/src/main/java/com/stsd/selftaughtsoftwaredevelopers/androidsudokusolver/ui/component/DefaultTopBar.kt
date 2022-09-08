package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.animation.enterIn
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.animation.exitOut
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.colors
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.padding
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.sizing

@Composable
fun DefaultTopBar(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = colors.primary,
    placementSpeed: TimeState,
    isCameraOn: Boolean,
    toggleCamera: () -> Unit,
    toggleSolutionSpeed: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(sizing.normal_bar)
            .background(color)
            .padding(horizontal = padding.small),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        DefaultIconButton(
            enabled = enabled,
            imageVector = when (placementSpeed) {
                TimeState.INSTANT_SPEED -> rounded.Timer3
                TimeState.SUPER_SPEED -> rounded.Timer10
                TimeState.SLOW_SPEED -> rounded.Snooze
                else -> rounded.Timer
            }
        ) { toggleSolutionSpeed() }

        DefaultIconButton(
            enabled = enabled,
            imageVector = if (isCameraOn) {
                rounded.PhotoCamera
            } else {
                rounded.NoPhotography
            }
        ) { toggleCamera() }

    }

}

@Composable
fun MoreOptionsBar(
    modifier: Modifier = Modifier,
    iconList: List<TimeState>,
    dismissMoreOptionsBar: (TimeState) -> Unit
) {

    val isVisible by remember(iconList) { derivedStateOf { iconList.isNotEmpty() } }

    AnimatedVisibility(
        modifier = modifier,
        visible = isVisible,
        enter = enterIn(),
        exit = exitOut()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(sizing.normal_bar)
                .background(colors.primary)
                .padding(horizontal = padding.small),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            iconList.forEach { item ->

                DefaultIconButton(imageVector = item.icon) {
                    dismissMoreOptionsBar(item)
                }

            }

        }

    }

}