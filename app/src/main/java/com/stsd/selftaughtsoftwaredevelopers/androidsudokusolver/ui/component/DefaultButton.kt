package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

@Composable
fun ThemedNumericButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    numericValue: Int,
    onClick: (String) -> Unit
) {

    TextButton(
        modifier = modifier
            .padding(spacing.small)
            .clip(shape = shapes.small)
            .size(sizes.xLarge),
        enabled = enabled,
        colors = buttonColors(Black.copy(alpha = 0.05F)),
        onClick = { onClick(numericValue.toString()) }
    ) {
        Text(
            text = numericValue.toString(),
            textAlign = TextAlign.Center,
            style = MaterialTheme
                .typography
                .body1.copy(fontWeight = FontWeight.Black)
        )
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

@Composable
fun ThemedIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    imageVector: ImageVector,
    backgroundColor: Color,
    onClick: () -> Unit
) {

    IconButton(
        modifier = modifier
            .padding(spacing.small)
            .clip(shape = shapes.small)
            .drawBehind { drawRect(color = backgroundColor) },
        enabled = enabled,
        onClick = { onClick() }
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = imageVector.name
        )
    }

}