package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.Camera

@Composable
fun SudokuScannerScreen() {

    BoxWithConstraints {
        Camera()
    }

}