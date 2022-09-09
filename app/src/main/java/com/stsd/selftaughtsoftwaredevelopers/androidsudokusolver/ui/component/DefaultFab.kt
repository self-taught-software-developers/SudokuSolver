package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.IconItem
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme

@Composable
fun DefaultFab(
    enabled : Boolean = true,
    items: List<IconItem>,
    backgroundColor: Color = Color.Black.copy(alpha = 0.05F)
) {

    Row(
        modifier = Modifier
            .clip(CircleShape)
            .drawBehind { drawRect(color = backgroundColor) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(CustomTheme.padding.small)
    ) {
        items.forEach { item ->

            item.icon?.let { icon ->
                DefaultIconButton(
                    enabled = enabled,
                    imageVector = icon
                ) { item.onClick() }
            }
            
            item.textId?.let { id ->
                DefaultButton(text = stringResource(id = id)) {
                    item.onClick()
                }
            }

        }
    }

}
