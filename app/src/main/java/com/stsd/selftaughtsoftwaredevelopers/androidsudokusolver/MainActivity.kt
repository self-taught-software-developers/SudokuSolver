package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.navigation.SudokuSolverApp
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.AndroidSudokuSolverTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.viewmodel.SudokuViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val vm by viewModels<SudokuViewModel>()
    private var isLoading: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition { isLoading }
        }
        super.onCreate(savedInstanceState)

        setContent {

            val boardState by vm.uiBoardState.collectAsState()

            LaunchedEffect(boardState) {
                if (boardState.isLoading()) {
                    vm.boardIsLoaded()
                } else {
                    if (isLoading) {
                        delay(200L)
                        isLoading = boardState.isLoading()
                    }
                }
            }

            if(!boardState.isLoading()) {
                AndroidSudokuSolverTheme {
                    SudokuSolverApp(
                        boardState = boardState
                    ) { vm.updatePlacementSpeed(it) }
                }
            }

        }

    }
}