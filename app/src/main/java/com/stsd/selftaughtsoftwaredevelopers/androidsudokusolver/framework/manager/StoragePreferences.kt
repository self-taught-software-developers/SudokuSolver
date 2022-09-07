package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.GridState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoragePreferences @Inject constructor(
    private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>
) {

    private val solutionKey = stringPreferencesKey("SOLUTION_SPEED_PREF_KEY")
    val solutionSpeed : Flow<TimeState> = dataStore.data.map { pref ->
        when(pref[solutionKey]) {
            TimeState.INSTANT_SPEED.name -> TimeState.INSTANT_SPEED
            TimeState.SUPER_SPEED.name -> TimeState.SUPER_SPEED
            TimeState.SLOW_SPEED.name -> TimeState.SLOW_SPEED
            else -> TimeState.DEFAULT_SPEED
        }
    }
    suspend fun updateSolutionSpeed(value : TimeState) {
        dataStore.edit { prefs ->
            prefs[solutionKey] = value.name
        }
    }
    private val gridKey = stringPreferencesKey("GRID_DIMENSIONS_PREF_KEY")
    val gridDimensions : Flow<GridState> = dataStore.data.map { pref ->
        when(pref[gridKey]) {
            GridState.GRID_2X2.name -> GridState.GRID_2X2
            GridState.GRID_4X4.name -> GridState.GRID_4X4
            else -> GridState.GRID_3X3
        }
    }
    suspend fun updateGridDimensions(value : GridState) {
        dataStore.edit { prefs ->
            prefs[gridKey] = value.name
        }
    }

}