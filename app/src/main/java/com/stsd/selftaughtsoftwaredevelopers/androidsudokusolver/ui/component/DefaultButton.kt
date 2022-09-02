package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.padding
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.shapes
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.sizing

@Composable
fun DefaultNumericButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    numericValue: Int,
    onClick: (String) -> Unit
) {

    TextButton(
        modifier = modifier
            .padding(padding.small)
            .clip(shape = shapes.small)
            .size(sizing.xx_large),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(Color.Black.copy(alpha = 0.05F)),
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
fun DefaultIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    imageVector: ImageVector,
    onClick: () -> Unit
) {

    IconButton(
        modifier = modifier,
        enabled = enabled,
        onClick = { onClick() }
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = imageVector.name
        )
    }

}

@Composable
fun DefaultIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    imageVector: ImageVector,
    backgroundColor: Color,
    onClick: () -> Unit
) {

    IconButton(
        modifier = modifier
            .padding(padding.small)
            .clip(shape = CustomTheme.shapes.small)
            .background(backgroundColor)
            .size(CustomTheme.sizing.xx_large),
        enabled = enabled,
        onClick = { onClick() }
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = imageVector.name
        )
    }

}