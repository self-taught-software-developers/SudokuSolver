package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class IconItem(
    @StringRes val textId: Int? = null,
    val icon: ImageVector? = null,
    val onClick: () -> Unit
)