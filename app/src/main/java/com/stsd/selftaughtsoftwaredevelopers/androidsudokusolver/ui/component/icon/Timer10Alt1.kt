package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.icon

import androidx.compose.material.Icon
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

val Timer10Alt1: ImageVector
    get() {
        if (_timer10Alt1 != null) {
            return _timer10Alt1!!
        }
        _timer10Alt1 = materialIcon(name = "Timer10Alt1") {
            materialPath {
                moveTo(8.75f, 17.225f)
                horizontalLineToRelative(1.8f)
                verticalLineToRelative(-8.45f)
                horizontalLineToRelative(-3.0f)
                lineTo(7.55f, 10.6f)
                horizontalLineToRelative(1.2f)
                close()
                moveTo(13.2f, 17.225f)
                horizontalLineToRelative(1.2f)
                quadToRelative(0.75f, 0.0f, 1.288f, -0.525f)
                quadToRelative(0.537f, -0.525f, 0.537f, -1.275f)
                lineTo(16.225f, 10.6f)
                quadToRelative(0.0f, -0.75f, -0.537f, -1.288f)
                quadToRelative(-0.538f, -0.537f, -1.288f, -0.537f)
                horizontalLineToRelative(-1.2f)
                quadToRelative(-0.75f, 0.0f, -1.275f, 0.537f)
                quadToRelative(-0.525f, 0.538f, -0.525f, 1.288f)
                verticalLineToRelative(4.825f)
                quadToRelative(0.0f, 0.75f, 0.525f, 1.275f)
                quadToRelative(0.525f, 0.525f, 1.275f, 0.525f)
                close()
                moveTo(13.2f, 15.425f)
                lineTo(13.2f, 10.6f)
                horizontalLineToRelative(1.2f)
                verticalLineToRelative(4.825f)
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
            }
        }
        return _timer10Alt1!!
    }
private var _timer10Alt1: ImageVector? = null


@Preview
@Composable
fun Timer10Alt1Preview() {
    Icon(
        imageVector = Timer10Alt1,
        contentDescription = Timer10Alt1.name
    )
}