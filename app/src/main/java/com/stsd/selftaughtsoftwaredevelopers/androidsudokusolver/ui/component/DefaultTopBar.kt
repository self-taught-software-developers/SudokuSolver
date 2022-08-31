package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NoPhotography
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.animation.enterIn
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.animation.exitOut
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.GridState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.IconItem
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.ScannerState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.colors
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.padding
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.sizing

@Composable
fun DefaultTopBar(
    timeState: TimeState,
    gridState: GridState,
    cameraState: ScannerState,
    toggleSolutionSpeed: () -> Unit,
    toggleGridDimens: () -> Unit,
    toggleCamera: () -> Unit
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

        DefaultIconButton(
            imageVector = when (timeState) {
                TimeState.INSTANT_SPEED -> rounded.Timer3
                TimeState.SUPER_SPEED -> rounded.Timer10
                TimeState.SLOW_SPEED -> rounded.Snooze
                else -> rounded.Timer
            }
        ) { toggleSolutionSpeed() }

        DefaultIconButton(
            imageVector = when (gridState) {
                GridState.GRID_2X2 -> Grid2x2
                GridState.GRID_3X3 -> rounded.Grid3x3
                GridState.GRID_4X4 -> rounded.Grid4x4
            }
        ) { toggleGridDimens() }

        DefaultIconButton(
            imageVector = when (cameraState) {
                ScannerState.SCANNING -> Icons.Outlined.PhotoCamera
                ScannerState.IDLE -> Icons.Outlined.PhotoCamera
                ScannerState.OFF -> Icons.Outlined.NoPhotography
            }
        ) { toggleCamera() }

    }
    
}

@Composable
fun MoreOptionsBar(
    modifier: Modifier = Modifier,
    iconList: MutableState<List<IconItem>>
) {

    AnimatedVisibility(
        modifier = modifier,
        visible = iconList.value.isNotEmpty(),
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

            iconList.value.forEach { item ->

                DefaultIconButton(imageVector = item.icon) {
                    item.onClick()
                    iconList.value = listOf()
                }

            }

        }

    }

}
