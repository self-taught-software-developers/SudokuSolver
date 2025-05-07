package com.stsd.selftaughtsoftwaredevelopers.shared.ui.navigation.graph

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cerve.development.ui.navigation.TypeSafeAppDestination
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.navigation.destination.AppDestinations.SettingsDestination

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
