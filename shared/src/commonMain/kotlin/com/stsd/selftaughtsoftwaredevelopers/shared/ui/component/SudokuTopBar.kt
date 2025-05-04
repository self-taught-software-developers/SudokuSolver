package com.stsd.selftaughtsoftwaredevelopers.shared.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import com.cerve.development.ui.component.CerveIconButton
import com.cerve.development.ui.component.CerveTopAppBar
import com.stsd.selftaughtsoftwaredevelopers.resources.Res
import com.stsd.selftaughtsoftwaredevelopers.resources.app_name
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.TimeState
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SudokuTopBar(
    modifier: Modifier = Modifier,
    onNavigateToSettings: () -> Unit
) {

    CerveTopAppBar(
        modifier = modifier,
        title = stringResource(Res.string.app_name),
        actions = {
            CerveIconButton(
                imageVector = Icons.Default.Settings
            ) { onNavigateToSettings() }
        }
    )

}
