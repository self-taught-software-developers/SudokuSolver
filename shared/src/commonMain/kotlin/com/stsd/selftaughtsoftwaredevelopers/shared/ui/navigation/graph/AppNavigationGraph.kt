package com.stsd.selftaughtsoftwaredevelopers.shared.ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.cerve.development.ui.navigation.TypeSafeAppDestination
import com.stsd.selftaughtsoftwaredevelopers.shared.di.createKoinConfiguration
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.navigation.destination.AppDestinations
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.theme.SudokuSolverTheme
import org.koin.compose.KoinMultiplatformApplication
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun AppNavigationGraph(
    startDestination: TypeSafeAppDestination = AppDestinations.SolverDestination
) {

    SudokuSolverTheme {
        KoinMultiplatformApplication(config = createKoinConfiguration()) {
            val navController: NavHostController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                SudokuSolverHubNavigationGraph(navController)
                SudokuSettingsHubNavigationGraph(navController)
            }
        }
    }
}