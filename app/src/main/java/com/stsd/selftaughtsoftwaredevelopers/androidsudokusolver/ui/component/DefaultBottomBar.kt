package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.rounded.ClearAll
import androidx.compose.material.icons.rounded.ForwardToInbox
import androidx.compose.material.icons.rounded.PlayCircleOutline
import androidx.compose.material.icons.rounded.Undo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.co.material3extension.designsystem.ExtendedTheme.spacing
import com.cerve.co.material3extension.designsystem.rounded
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.IconItem
import kotlinx.collections.immutable.persistentListOf

@Composable
fun DefaultBottomBar(
    color: @Composable () -> Color,
    onFeatureRequest: () -> Unit,
    onUndoLastClick: () -> Unit,
    onUndoAllClick: () -> Unit,
    onClickSolve: () -> Unit,
    onEnterValue: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            LandscapeBottomBar(
                color = color,
                onFeatureRequest = onFeatureRequest,
                onUndoLastClick = onUndoLastClick,
                onUndoAllClick = onUndoAllClick,
                onClickSolve = onClickSolve,
                onEnterValue = onEnterValue,
                modifier = modifier.fillMaxWidth(0.3f).fillMaxHeight(),
                enabled = enabled
            )
        }
        else -> {
            PortraitBottomBar(
                color = color,
                onFeatureRequest = onFeatureRequest,
                onUndoLastClick = onUndoLastClick,
                onUndoAllClick = onUndoAllClick,
                onClickSolve = onClickSolve,
                onEnterValue = onEnterValue,
                modifier = modifier,
                enabled = enabled
            )
        }
    }


}

@Composable
fun LandscapeBottomBar(
    color: @Composable () -> Color,
    onFeatureRequest: () -> Unit,
    onUndoLastClick: () -> Unit,
    onUndoAllClick: () -> Unit,
    onClickSolve: () -> Unit,
    onEnterValue: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        ThemedVerticalDivider(color = color())
        Column(
            modifier = Modifier.padding(vertical = spacing.small),
            verticalArrangement = Arrangement.spacedBy(spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            ThemedFab(modifier = Modifier
                .themedBorder(color = color())
                .align(Alignment.CenterHorizontally),
                enabled = enabled,
                color = { color() },
                items = {
                    persistentListOf(
                        IconItem(
                            icon = rounded.ForwardToInbox, onClick = onFeatureRequest
                        ), IconItem(
                            icon = rounded.Undo, onClick = onUndoLastClick
                        ), IconItem(
                            icon = rounded.ClearAll, onClick = onUndoAllClick
                        )
                    )
                })

            FlowRow(
                modifier = Modifier,
                mainAxisSize = SizeMode.Expand,
                mainAxisAlignment = FlowMainAxisAlignment.Center,
                crossAxisAlignment = FlowCrossAxisAlignment.Center
            ) {
                repeat(9) {
                    ThemedNumericButton(
                        modifier = Modifier
                            .padding(spacing.small)
                            .themedBorder(color = color()),
                        enabled = enabled,
                        backgroundColor = color(),
                        numericValue = (it + 1),
                        onClick = onEnterValue
                    )
                }

                ThemedIconButton(
                    modifier = Modifier
                        .padding(spacing.small)
                        .themedBorder(color = color()),
                    enabled = enabled,
                    backgroundColor = color(),
                    imageVector = rounded.PlayCircleOutline,
                    onClick = onClickSolve
                )
            }
        }


    }
}

@Composable
fun PortraitBottomBar(
    color: @Composable () -> Color,
    onFeatureRequest: () -> Unit,
    onUndoLastClick: () -> Unit,
    onUndoAllClick: () -> Unit,
    onClickSolve: () -> Unit,
    onEnterValue: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {

    Column(
        modifier = modifier.padding(vertical = spacing.small),
        verticalArrangement = Arrangement.spacedBy(spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ThemedDivider(color = color())

        ThemedFab(modifier = Modifier
            .themedBorder(color = color())
            .align(Alignment.CenterHorizontally),
            enabled = enabled,
            color = { color() },
            items = {
                persistentListOf(
                    IconItem(
                        icon = rounded.ForwardToInbox, onClick = onFeatureRequest
                    ), IconItem(
                        icon = rounded.Undo, onClick = onUndoLastClick
                    ), IconItem(
                        icon = rounded.ClearAll, onClick = onUndoAllClick
                    )
                )
            })

        FlowRow(
            modifier = Modifier,
            mainAxisSize = SizeMode.Expand,
            mainAxisAlignment = FlowMainAxisAlignment.Center,
            crossAxisAlignment = FlowCrossAxisAlignment.Center
        ) {
            repeat(9) {
                ThemedNumericButton(
                    modifier = Modifier
                        .padding(spacing.small)
                        .themedBorder(color = color()),
                    enabled = enabled,
                    backgroundColor = color(),
                    numericValue = (it + 1),
                    onClick = onEnterValue
                )
            }

            ThemedIconButton(
                modifier = Modifier
                    .padding(spacing.small)
                    .themedBorder(color = color()),
                enabled = enabled,
                backgroundColor = color(),
                imageVector = rounded.PlayCircleOutline,
                onClick = onClickSolve
            )
        }
    }
}

@Preview
@Composable
fun DefaultBottomBarPreview() {
    DefaultBottomBar(onUndoAllClick = { },
        onUndoLastClick = { },
        onFeatureRequest = { },
        onClickSolve = { },
        onEnterValue = { },
        color = { Color.Green })
}
