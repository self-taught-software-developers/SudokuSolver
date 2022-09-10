package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.icon

import androidx.compose.material.Icon
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

val TimeAuto: ImageVector
    get() {
        if (_timeAuto != null) {
            return _timeAuto!!
        }
        _timeAuto = materialIcon(name = "TimeAuto") {
            materialPath {
                moveTo(8.0f, 17.0f)
                horizontalLineToRelative(1.85f)
                lineToRelative(0.575f, -1.75f)
                horizontalLineToRelative(3.1f)
                lineTo(14.1f, 17.0f)
                lineTo(16.0f, 17.0f)
                lineToRelative(-3.0f, -8.45f)
                horizontalLineToRelative(-2.0f)
                close()
                moveTo(10.9f, 13.8f)
                lineTo(12.0f, 10.5f)
                lineTo(13.075f, 13.8f)
                close()
                moveTo(9.0f, 3.0f)
                lineTo(9.0f, 1.0f)
                horizontalLineToRelative(6.0f)
                verticalLineToRelative(2.0f)
                close()
                moveTo(12.0f, 22.0f)
                quadToRelative(-1.85f, 0.0f, -3.488f, -0.712f)
                quadToRelative(-1.637f, -0.713f, -2.862f, -1.938f)
                reflectiveQuadToRelative(-1.938f, -2.862f)
                quadTo(3.0f, 14.85f, 3.0f, 13.0f)
                reflectiveQuadToRelative(0.712f, -3.488f)
                quadTo(4.425f, 7.875f, 5.65f, 6.65f)
                reflectiveQuadToRelative(2.862f, -1.937f)
                quadTo(10.15f, 4.0f, 12.0f, 4.0f)
                quadToRelative(1.55f, 0.0f, 2.975f, 0.5f)
                reflectiveQuadToRelative(2.675f, 1.45f)
                lineToRelative(1.4f, -1.4f)
                lineToRelative(1.4f, 1.4f)
                lineToRelative(-1.4f, 1.4f)
                quadTo(20.0f, 8.6f, 20.5f, 10.025f)
                quadTo(21.0f, 11.45f, 21.0f, 13.0f)
                quadToRelative(0.0f, 1.85f, -0.712f, 3.488f)
                quadToRelative(-0.713f, 1.637f, -1.938f, 2.862f)
                reflectiveQuadToRelative(-2.862f, 1.938f)
                quadTo(13.85f, 22.0f, 12.0f, 22.0f)
                close()
                moveTo(12.0f, 20.0f)
                quadToRelative(2.9f, 0.0f, 4.95f, -2.05f)
                quadTo(19.0f, 15.9f, 19.0f, 13.0f)
                quadToRelative(0.0f, -2.9f, -2.05f, -4.95f)
                quadTo(14.9f, 6.0f, 12.0f, 6.0f)
                quadTo(9.1f, 6.0f, 7.05f, 8.05f)
                quadTo(5.0f, 10.1f, 5.0f, 13.0f)
                quadToRelative(0.0f, 2.9f, 2.05f, 4.95f)
                quadTo(9.1f, 20.0f, 12.0f, 20.0f)
                close()
                moveTo(12.0f, 13.0f)
                close()
            }
        }
        return _timeAuto!!
    }

private var _timeAuto: ImageVector? = null

@Preview
@Composable
fun TimeAutoPreview() {
    Icon(
        imageVector = TimeAuto,
        contentDescription = TimeAuto.name
    )
}