package com.stsd.selftaughtsoftwaredevelopers.shared.ui.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cerve.development.ui.navigation.TypeSafeAppDestination
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.navigation.destination.AppDestinations.SettingsDestination

fun NavGraphBuilder.sudokuSettingsHubNavigationGraph(
    navController: NavController,
    startDestination: TypeSafeAppDestination = SettingsDestination.Hub
) = navigation<SettingsDestination>(
    startDestination = startDestination
) {

    composable<SettingsDestination.Hub> {

    }

}
