package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.navigation.NavigationRoutes.SOLVER_DESTINATION
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen.SudokuSolverScreen
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.viewmodel.SudokuViewModel
import java.sql.Time

@Composable
fun SudokuSolverApp(
    navController: NavHostController = rememberNavController(),
    solutionSpeed: TimeState?,
    updatePlacementSpeed: (TimeState) -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = SOLVER_DESTINATION
    ) {

        composable(SOLVER_DESTINATION) {
//            val vm: SudokuViewModel = hiltViewModel()

            SudokuSolverScreen(solutionSpeedState = solutionSpeed) { speed ->
                updatePlacementSpeed(speed)
            }

        }

    }

}