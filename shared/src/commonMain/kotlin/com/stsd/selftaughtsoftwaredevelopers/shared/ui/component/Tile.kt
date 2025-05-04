package com.stsd.selftaughtsoftwaredevelopers.shared.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.cerve.development.ui.component.theme.ExtendedTheme
import com.cerve.development.ui.component.theme.ExtendedTheme.colors
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.TileState.Companion.toTileText

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Tile(
    value: Int,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    Surface(
        modifier = modifier
            .clickable { onClick() }
            .drawWithCache {
                onDrawBehind {
                    drawRect(color)
                }
            },
        color = Color.Transparent,
        contentColor = colors.onSurface
    ) {
        AnimatedContent(
            targetState = value,
            transitionSpec = {
                // Compare the incoming number with the previous number.
                if (targetState > initialState) {
                    // If the target number is larger, it slides up and fades in
                    // while the initial (smaller) number slides up and fades out.
                    slideInVertically { height -> height } + fadeIn() with
                            slideOutVertically { height -> -height } + fadeOut()
                } else {
                    // If the target number is smaller, it slides down and fades in
                    // while the initial number slides down and fades out.
                    slideInVertically { height -> -height } + fadeIn() with
                            slideOutVertically { height -> height } + fadeOut()
                }.using(
                    // Disable clipping since the faded slide-in/out should
                    // be displayed out of bounds.
                    SizeTransform(clip = false)
                )
            }
        ) { targetCount ->
            Text(
                modifier = Modifier.wrapContentHeight(),
                text = toTileText(targetCount),
                textAlign = TextAlign.Center,
                style = ExtendedTheme
                    .typography
                    .titleMedium
            )
        }
    }
}