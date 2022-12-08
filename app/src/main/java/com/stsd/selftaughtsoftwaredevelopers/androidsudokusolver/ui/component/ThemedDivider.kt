package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.cerve.co.material3extension.designsystem.ExtendedTheme

@Composable
fun ThemedDivider(
    modifier: Modifier = Modifier,
    color: Color = ExtendedTheme.colors.primary,
    alpha: Float = ExtendedTheme.alphas.medium_30,
    thickness: Dp = ExtendedTheme.sizes.xSmall
) = Divider(
    modifier = modifier,
    thickness = thickness,
    color = color.copy(alpha = alpha)
)


@Composable
fun ThemedVerticalDivider(
    modifier: Modifier = Modifier,
    color: Color = ExtendedTheme.colors.primary,
    alpha: Float = ExtendedTheme.alphas.medium_30,
    thickness: Dp = ExtendedTheme.sizes.xSmall
) = Divider(
    modifier = modifier
        .fillMaxHeight()
        .width(thickness),
    color = color.copy(alpha = alpha)
)
