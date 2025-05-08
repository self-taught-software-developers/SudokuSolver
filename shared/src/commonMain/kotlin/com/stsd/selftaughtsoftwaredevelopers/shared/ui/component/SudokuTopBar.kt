package com.stsd.selftaughtsoftwaredevelopers.shared.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cerve.development.ui.component.CerveTopAppBar
import com.stsd.selftaughtsoftwaredevelopers.resources.Res
import com.stsd.selftaughtsoftwaredevelopers.resources.app_name
import org.jetbrains.compose.resources.stringResource

@Composable
fun SudokuTopBar(
    modifier: Modifier = Modifier,
    onNavigateToSettings: () -> Unit
) {

    CerveTopAppBar(
        modifier = modifier,
        title = stringResource(Res.string.app_name),
        actions = {
//            CerveIconButton(
//                imageVector = Icons.Default.Settings
//            ) { onNavigateToSettings() }
        }
    )

}
