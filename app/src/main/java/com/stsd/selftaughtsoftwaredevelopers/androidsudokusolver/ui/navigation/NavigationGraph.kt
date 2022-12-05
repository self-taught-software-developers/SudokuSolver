package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    boardState: BoardState,
    onSolveBoardClick: () -> Unit,
    onUpdateValueClick: (String) -> Unit,
    onUndoLastClick: () -> Unit,
    onUndoAllClick: () -> Unit,
    onPlacementUpdate: (TimeState) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SOLVER_DESTINATION
    ) {

        composable(SOLVER_DESTINATION) {
            SudokuSolverScreen(
                boardState = boardState,
                onSolveBoardClick = { onSolveBoardClick() },
                onUpdateValueClick = { onUpdateValueClick(it) },
                onUndoLastClick = { onUndoLastClick() },
                onUndoAllClick = { onUndoAllClick() },
                onSolutionSpeedUpdate = { speed -> onPlacementUpdate(speed) }
            )
        }

    }

}