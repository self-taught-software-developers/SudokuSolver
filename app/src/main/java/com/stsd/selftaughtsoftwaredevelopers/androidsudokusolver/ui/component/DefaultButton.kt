package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiObjects
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme.padding

@Composable
fun DefaultNumericButton(
    modifier: Modifier = Modifier,
    num: Int,
    onClick: (String) -> Unit
) {

    Box(
        modifier = modifier
            .padding(padding.small)
            .clip(shape = CustomTheme.shapes.small)
            .background(CustomTheme.colors.onSurface.copy(alpha = 0.06F))
            .clickable { onClick(num.toString()) }
            .size(CustomTheme.sizing.xx_large),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = num.toString(),
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
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(padding.small)
            .clip(shape = CustomTheme.shapes.small)
            .clickable { onClick() }
            .size(CustomTheme.sizing.xx_large),
        contentAlignment = Alignment.Center
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
    imageVector: ImageVector,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(padding.small)
            .clip(shape = CustomTheme.shapes.small)
            .background(backgroundColor)
            .clickable { onClick() }
            .size(CustomTheme.sizing.xx_large),
        contentAlignment = Alignment.Center
    ) {

        Icon(
            imageVector = imageVector,
            contentDescription = imageVector.name
        )

    }

}