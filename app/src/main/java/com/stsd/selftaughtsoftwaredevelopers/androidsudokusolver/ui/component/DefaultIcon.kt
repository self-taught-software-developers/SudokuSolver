package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.padding
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.sizing
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.typography

val rounded = Icons.Rounded

val Grid2x2 : ImageVector
    get() {
        if (_grid2x2 != null) {
            return _grid2x2!!
        }
        _grid2x2 = materialIcon(name = "Rounded.Grid2x2") {
            //TODO - modify this window icon to match grid3x3 and grid4x4
            materialPath {
                moveTo(20.0f, 2.0f)
                horizontalLineTo(4.0f)
                curveTo(2.9f, 2.0f, 2.0f, 2.9f, 2.0f, 4.0f)
                verticalLineToRelative(16.0f)
                curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f)
                horizontalLineToRelative(16.0f)
                curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f)
                verticalLineTo(4.0f)
                curveTo(22.0f, 2.9f, 21.1f, 2.0f, 20.0f, 2.0f)
                close()
                moveTo(20.0f, 11.0f)
                horizontalLineToRelative(-7.0f)
                verticalLineTo(4.0f)
                horizontalLineToRelative(7.0f)
                verticalLineTo(11.0f)
                close()
                moveTo(11.0f, 4.0f)
                verticalLineToRelative(7.0f)
                horizontalLineTo(4.0f)
                verticalLineTo(4.0f)
                horizontalLineTo(11.0f)
                close()
                moveTo(4.0f, 13.0f)
                horizontalLineToRelative(7.0f)
                verticalLineToRelative(7.0f)
                horizontalLineTo(4.0f)
                verticalLineTo(13.0f)
                close()
                moveTo(13.0f, 20.0f)
                verticalLineToRelative(-7.0f)
                horizontalLineToRelative(7.0f)
                verticalLineToRelative(7.0f)
                horizontalLineTo(13.0f)
                close()
            }
        }
        return _grid2x2!!
    }

private var _grid2x2: ImageVector? = null

@Preview
@Composable
fun IconPreview() {

    Icon(
        imageVector = Grid2x2,
        contentDescription = Grid2x2.name
    )

}

@Composable
fun IconTitle(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(sizing.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Surface(
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onClick() },
            color = Color.Black.copy(alpha = 0.1F)
        ) {
            Icon(
                modifier = Modifier
                    .padding(padding.medium)
                    .size(sizing.default_icon_x2),
                imageVector = icon,
                contentDescription = icon.name
            )
        }

        Text(
            modifier = modifier.padding(padding.medium),
            text = text,
            textAlign = TextAlign.Center,
            style = typography.h6
        )
    }

}

@Preview
@Composable
fun IconTitlePreview() {
   IconTitle(
       icon = Grid2x2,
       text = Grid2x2.name
   ) {

   }
}