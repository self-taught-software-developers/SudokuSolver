package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.animation.enterIn
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.animation.exitOut
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.GridState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TimeState
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.colors
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.padding
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.sizing
import java.sql.Time

@Composable
fun DefaultTopBar(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,

) {

//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .requiredHeight(sizing.default_top_bar)
//            .background(color)
//            .padding(horizontal = padding.small),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//
//        DefaultIconButton(
//            enabled = enabled,
//            imageVector = placementSpeed.icon
//        ) { toggleSolutionSpeed() }
//
//    }

}

@Composable
fun MoreOptionsBar(
    modifier: Modifier = Modifier,
    items: List<Enum<*>>,
    dismissMoreOptionsBar: (TimeState) -> Unit
) {

    var expanded by remember { mutableStateOf<Enum<*>?>(null) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(sizing.default_top_bar)
            .background(colors.primary)
            .padding(horizontal = padding.small),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        AnimatedVisibility(
            modifier = modifier,
            visible = expanded == null,
            enter = enterIn(),
            exit = exitOut()
        ) {

            items.forEach { item ->
                when(item) {
                    is TimeState -> DefaultIconButton(imageVector = item.icon) {
                        expanded = item
                    }
                }

                /**
                 * onClick() -> add
                 */
                //TIMES_STATE
                //GRID_STATE

            }

        }

        expanded?.let { value ->
            when(value) {
                is TimeState -> TimeState.values().forEach {
                    DefaultIconButton(imageVector = it.icon) {
                        expanded = null
                    }
                }
            }
        }


    }

}