package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.camera.core.CameraState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.ScannerState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.AndroidSudokuSolverTheme

@Composable
fun DefaultTopBar(
    cameraState: ScannerState,
    toggleCamera: () -> Unit
) {
    
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CompositionLocalProvider(
                LocalContentColor provides if(isSystemInDarkTheme()) {
                    Color.White
                } else {
                    Color.Black
                }
            ) {
                DefaultIconButton(imageVector = Icons.Outlined.Timer) {

                }

                DefaultIconButton(imageVector = Icons.Outlined.Grid3x3) {

                }

                DefaultIconButton(
                    imageVector = when (cameraState) {
                        ScannerState.SCANNING -> Icons.Outlined.PhotoCamera
                        ScannerState.IDLE -> Icons.Outlined.PhotoCamera
                        ScannerState.OFF -> Icons.Outlined.NoPhotography
                    }
                ) { toggleCamera() }

                DefaultIconButton(imageVector = Icons.Outlined.MoreVert) {

                }
            }

        }

    }
    
}

@Preview
@Composable
fun DefaultTopBarPreview() {
    AndroidSudokuSolverTheme {
        DefaultTopBar(ScannerState.IDLE) {

        }
    }
}