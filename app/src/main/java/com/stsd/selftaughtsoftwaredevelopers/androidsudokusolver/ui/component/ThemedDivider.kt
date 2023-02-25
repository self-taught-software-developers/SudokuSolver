package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.cerve.co.material3extension.designsystem.ExtendedTheme.alphas
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.cerve.co.material3extension.designsystem.ExtendedTheme.shapes
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes

@Composable
fun ThemedDivider(
    modifier: Modifier = Modifier,
    color: Color = colors.primary,
    alpha: Float = alphas.medium_30,
    thickness: Dp = sizes.xSmall
) = Divider(
    modifier = modifier,
    thickness = thickness,
    color = color.copy(alpha = alpha)
)

@Composable
fun ThemedVerticalDivider(
    modifier: Modifier = Modifier,
    color: Color = colors.primary,
    alpha: Float = alphas.medium_30,
    thickness: Dp = sizes.xSmall
) = Divider(
    modifier = modifier
        .fillMaxHeight()
        .width(thickness),
    color = color.copy(alpha = alpha)
)

@Composable
fun themedBorder(
    color: Color? = null,
    alpha: Float? = null,
    thickness: Dp? = null
) = BorderStroke(
    width = thickness ?: sizes.xSmall,
    color = (color ?: colors.primary).copy(alpha = (alpha ?: alphas.medium_30))

)

fun Modifier.themedBorder(
    color: Color? = null,
    alpha: Float? = null,
    thickness: Dp? = null,
    shape: Shape? = null
) = composed {
    this.border(
        shape = shape ?: shapes.small,
        width = thickness ?: sizes.xSmall,
        color = (color ?: colors.primary).copy(alpha = (alpha ?: alphas.medium_30))
    )
}
