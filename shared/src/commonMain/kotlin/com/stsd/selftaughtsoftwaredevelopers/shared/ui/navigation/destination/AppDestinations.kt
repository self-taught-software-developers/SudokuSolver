package com.stsd.selftaughtsoftwaredevelopers.shared.ui.navigation.destination

import com.cerve.development.ui.navigation.TypeSafeAppDestination
import kotlinx.serialization.Serializable


@Serializable
object AppDestinations {

    @Serializable
    data object SolverDestination: TypeSafeAppDestination {

        @Serializable
        data object Hub: TypeSafeAppDestination
    }

    @Serializable
    data object SettingsDestination: TypeSafeAppDestination {

        @Serializable
        data object Hub: TypeSafeAppDestination
    }

}