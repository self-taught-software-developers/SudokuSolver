package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme

fun Modifier.defaultBorder(color: Color) : Modifier = composed {

   this.border(
        border = BorderStroke(
            width = CustomTheme.sizing.xx_small,
            color = color
        ),
        shape = CustomTheme.shapes.medium
    )

}