package com.stsd.selftaughtsoftwaredevelopers.shared.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

fun Modifier.defaultBorder(
    borderColor: Color,
    borderWidth: Dp,
    borderShape: CornerBasedShape
): Modifier = this
    .clip(shape = borderShape)
    .border(
        border = BorderStroke(
            width = borderWidth,
            color = borderColor
        ),
        shape = borderShape
    )
