package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.rounded.ClearAll
import androidx.compose.material.icons.rounded.Undo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.cerve.co.material3extension.designsystem.ExtendedTheme.alphas
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.cerve.co.material3extension.designsystem.ExtendedTheme.spacing
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.icon.rounded
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.IconItem
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.AllPreviews
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ThemedFab(
    enabled: Boolean,
    items: () -> ImmutableList<IconItem>,
    modifier: Modifier = Modifier,
    color: @Composable () -> Color
) {
    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(color().copy(alpha = alphas.small_10)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.small)
    ) {
        items().forEach { item ->

            item.icon?.let { icon ->
                ThemedIconButton(
                    enabled = { enabled },
                    icon = icon,
                    onClick = item.onClick
                )
            }
        }
    }
}

@AllPreviews
@Composable
fun ThemedFabPreview() {
    ThemedFab(
        enabled = true,
        items = {
            persistentListOf(
                IconItem(icon = rounded.Undo) { },
                IconItem(icon = rounded.ClearAll) { }
            )
        },
        color = { colors.primary }
    )
}
