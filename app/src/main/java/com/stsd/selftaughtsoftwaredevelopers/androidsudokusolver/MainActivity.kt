package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.navigation.SudokuSolverApp
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.AndroidSudokuSolverTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.viewmodel.SudokuViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val vm by viewModels<SudokuViewModel>()
    private var screenOff: State<TimeState?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition { true }
        super.onCreate(savedInstanceState)

        setContent {

            screenOff = vm.solutionSpeed.collectAsState(null)

            AndroidSudokuSolverTheme {
                SudokuSolverApp(
                    solutionSpeed = screenOff?.value,
                    updatePlacementSpeed = vm::updateSolutionSpeed
                )
            }
        }

    }
}