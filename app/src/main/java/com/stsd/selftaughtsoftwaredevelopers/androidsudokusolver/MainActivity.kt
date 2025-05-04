package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.navigation.graph.AppNavigationGraph
//import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.viewmodel.SudokuViewModel
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

//    private val vm by viewModels<SudokuViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply { setKeepOnScreenCondition { false } }
        enableEdgeToEdge()
        window.isNavigationBarContrastEnforced = false
        super.onCreate(savedInstanceState)

        setContent {
            AppNavigationGraph()
        }
    }
}
