package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.TwoRowsOfButtonsOffset
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.AndroidSudokuSolverTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidSudokuSolverTheme {
                TwoRowsOfButtonsOffset()
            }
        }
    }
}



