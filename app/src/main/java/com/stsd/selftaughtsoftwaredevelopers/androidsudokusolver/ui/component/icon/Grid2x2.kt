package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.icon.myiconpack

import androidx.compose.material.Icon
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

val Grid2x2 : ImageVector
    get() {
        if (_grid2x2 != null) {
            return _grid2x2!!
        }
        _grid2x2 = materialIcon(name = "Rounded.Grid2x2") {
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