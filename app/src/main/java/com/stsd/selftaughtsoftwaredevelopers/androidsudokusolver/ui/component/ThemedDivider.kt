package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.cerve.co.material3extension.designsystem.ExtendedTheme

@Composable
fun ThemedDivider(
    color: Color = ExtendedTheme.colors.primary,
    alpha: Float = ExtendedTheme.alphas.small_10,
    thickness: Dp = ExtendedTheme.sizes.small
) = Divider(thickness = thickness,color = color.copy(alpha = alpha))