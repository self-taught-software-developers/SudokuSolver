package com.stsd.selftaughtsoftwaredevelopers.shared.ui.navigation.graph

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cerve.development.ui.helper.StateWrapper
import com.cerve.development.ui.navigation.TypeSafeAppDestination
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.navigation.destination.AppDestinations.SolverDestination
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.screen.SudokuSolverScreen
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.state.SudokuSolverViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.sudokuSolverHubNavigationGraph(
    navController: NavController,
    startDestination: TypeSafeAppDestination = SolverDestination.Hub
) = navigation<SolverDestination>(
    startDestination = startDestination
) {

    composable<SolverDestination.Hub> {

        val vm = koinViewModel<SudokuSolverViewModel>()
        val uiState by vm.uiState.collectAsState()

        uiState.StateWrapper { state, isLoading ->
            SudokuSolverScreen(
                state = state,
                isLoading = isLoading,
                onSolveBoardClick = vm::solveBoard,
                onUpdateValueClick = vm::changeValue,
                onDeleteClick = vm::delete,
                onResetClick = vm::reset
            )
        }
    }

}
