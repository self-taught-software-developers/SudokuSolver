package com.stsd.selftaughtsoftwaredevelopers.shared.ui.navigation.graph

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cerve.development.ui.helper.StateWrapper
import com.cerve.development.ui.navigation.TypeSafeAppDestination
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.screen.SudokuSolverScreen
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.navigation.destination.AppDestinations
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.navigation.destination.AppDestinations.SettingsDestination
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.navigation.destination.AppDestinations.SolverDestination
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.state.SudokuSolverViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.SudokuSettingsHubNavigationGraph(
    navController: NavController,
    startDestination: TypeSafeAppDestination = SettingsDestination.Hub
) = navigation<SettingsDestination>(
    startDestination = startDestination
) {

    composable<SettingsDestination.Hub> {

    }

}
