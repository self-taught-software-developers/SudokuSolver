package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.cerve.co.material3extension.designsystem.ExtendedTheme.alphas
import com.cerve.co.material3extension.designsystem.ExtendedTheme.shapes
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.cerve.co.material3extension.designsystem.ExtendedTheme.spacing

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    onClick: () -> Unit
) {

    TextButton(
        modifier = modifier
            .padding(spacing.small)
            .clip(shape = shapes.small),
        enabled = enabled,
        colors = buttonColors(Black.copy(alpha = 0.05F)),
        onClick = { onClick() }
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme
                .typography
                .body1.copy(fontWeight = FontWeight.Black)
        )
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ThemedNumericButton(
    enabled: Boolean,
    numericValue: Int,
    modifier: Modifier = Modifier,
    backgroundColor: Color = ExtendedTheme.colors.primary,
    onClick: (String) -> Unit = { }
) {

    Surface(
        modifier = modifier.size(sizes.large + sizes.medium),
        shape = shapes.small,
        color = backgroundColor.copy(alpha = alphas.small_10),
        contentColor = contentColorFor(backgroundColor = backgroundColor),
        enabled = enabled,
        onClick = { onClick(numericValue.toString()) }
    ) {
        BoxWithConstraints(contentAlignment = Alignment.Center) {
            Text(
                text = numericValue.toString(),
                fontSize = (maxHeight / 2).value.sp
            )
        }
    }

}

@Composable
fun ThemedIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    icon: ImageVector,
    onClick: () -> Unit
) {

    IconButton(
        modifier = modifier,
        enabled = enabled,
        onClick = { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = icon.name
        )
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ThemedIconButton(
    enabled: Boolean = true,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    backgroundColor: Color = ExtendedTheme.colors.primary,
    onClick: () -> Unit = { }
) {

    Surface(
        modifier = modifier.size(sizes.large + sizes.medium),
        shape = shapes.small,
        color = backgroundColor.copy(alpha = alphas.medium_30),
        contentColor = contentColorFor(backgroundColor = backgroundColor),
        enabled = enabled,
        onClick = { onClick() }
    ) {
        BoxWithConstraints(contentAlignment = Alignment.Center) {
            Icon(
                modifier = Modifier.size((maxHeight / 2)),
                imageVector = imageVector,
                contentDescription = imageVector.name
            )
        }

    }

}