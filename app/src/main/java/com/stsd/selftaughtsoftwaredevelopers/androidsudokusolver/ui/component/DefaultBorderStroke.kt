package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import com.cerve.co.material3extension.designsystem.ExtendedTheme

fun Modifier.defaultBorder(color: Color): Modifier = composed {
    this.border(
        border = BorderStroke(
            width = ExtendedTheme.sizes.xSmall,
            color = color
        ),
        shape = ExtendedTheme.shapes.medium
    )
}
