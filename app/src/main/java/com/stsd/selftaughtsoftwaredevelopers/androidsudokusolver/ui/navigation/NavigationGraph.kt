package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.BoardState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.navigation.NavigationRoutes.SOLVER_DESTINATION
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen.SudokuSolverScreen

@Composable
fun SudokuSolverApp(
    navController: NavHostController = rememberNavController(),
    boardState: BoardState,
    updatePlacementSpeed: (TimeState) -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = SOLVER_DESTINATION
    ) {

        composable(SOLVER_DESTINATION) {
            SudokuSolverScreen(boardState = boardState) { speed ->
                updatePlacementSpeed(speed)
            }
        }

    }

}