package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.rounded.ClearAll
import androidx.compose.material.icons.rounded.Undo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.icon.rounded
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.IconItem
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.AndroidSudokuSolverTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.ExtendedTheme.alpha
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.ExtendedTheme.padding
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.AllPreviews
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ThemedFab(
    modifier: Modifier = Modifier,
    enabled : Boolean = true,
    items: () -> ImmutableList<IconItem>,
    backgroundColor: Color = Color.Black.copy(alpha = alpha.small_10)
) {

    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(backgroundColor),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(padding.small)
    ) {
        items().forEach { item ->

            item.icon?.let { icon ->
                ThemedIconButton(
                    enabled = enabled,
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

    AndroidSudokuSolverTheme {
        ThemedFab(items = { persistentListOf(
            IconItem(icon = rounded.Undo) { },
            IconItem(icon = rounded.ClearAll) { }
        )
        })
    }

}
