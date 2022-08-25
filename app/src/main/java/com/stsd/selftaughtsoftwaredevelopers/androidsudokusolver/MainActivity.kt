package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen.SudokuScannerScreen
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen.SudokuScreen
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.AndroidSudokuSolverTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidSudokuSolverTheme {
                SudokuScannerScreen()
            }
        }
    }
}


