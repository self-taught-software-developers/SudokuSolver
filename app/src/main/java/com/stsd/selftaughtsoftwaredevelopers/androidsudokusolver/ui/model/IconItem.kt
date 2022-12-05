package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector

@Immutable
data class IconItem(
    @StringRes val textId: Int? = null,
    val icon: ImageVector? = null,
    val onClick: () -> Unit
)
