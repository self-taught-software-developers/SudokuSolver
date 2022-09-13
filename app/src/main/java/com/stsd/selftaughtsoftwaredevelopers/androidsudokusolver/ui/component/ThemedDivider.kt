package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.ExtendedTheme

@Composable
fun ThemedDivider(
    color: Color = ExtendedTheme.colors.primary,
    alpha: Float = ExtendedTheme.alpha.small_10,
    thickness: Dp = ExtendedTheme.dims.small_x2
) = Divider(thickness = thickness,color = color.copy(alpha = alpha))