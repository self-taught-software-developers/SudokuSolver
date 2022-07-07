package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.LocalPadding
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.LocalSizing

@Composable
fun ButtonWithNumber(num: Int, onButtonClick: (String) -> Unit) {

    val sizing = LocalSizing.current
    val padding = LocalPadding.current

    Surface(
        modifier = Modifier
            .padding(padding.small)
            .clip(shape = CustomTheme.shapes.small)
            .clickable { onButtonClick(num.toString()) }
            .size(sizing.xxx_large),
        color = CustomTheme.colors.onSurface.copy(alpha = 0.05F)
    ) {

        Box(contentAlignment = Alignment.Center) {

            Text(
                text = num.toString(),
                textAlign = TextAlign.Center
            )

        }


    }
}


@Composable
fun TwoRowsOfButtonsOffset(onButtonClick: (String) -> Unit) {

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        mainAxisAlignment = MainAxisAlignment.Center
    ) {
        for (i in 1..9) {
            ButtonWithNumber(num = i) {
                onButtonClick(it)
            }
        }
    }
}

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    text: String,
    onClick: () -> Unit
) {

    val sizing = LocalSizing.current
    val padding = LocalPadding.current

    IconButton(
        onClick = { onClick() },
        modifier = modifier
            .defaultMinSize(sizing.xxx_large)
            .padding(padding.small)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = imageVector, contentDescription = imageVector.name
            )
            Text(
                text = text.uppercase(),
                style = MaterialTheme.typography.caption.copy(fontSize = 10.sp),
                overflow = TextOverflow.Ellipsis
            )
        }

    }

}

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    imageVector: ImageVector,
    text: String,
    onClick: () -> Unit
) {

    val sizing = LocalSizing.current
    val padding = LocalPadding.current

    Surface(
        color = backgroundColor,
        shape = CustomTheme.shapes.small
    ) {
        IconButton(
            onClick = { onClick() },
            modifier = modifier
                .defaultMinSize(sizing.xxx_large)
                .padding(padding.small)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = imageVector, contentDescription = imageVector.name
                )
                Text(
                    text = text.uppercase(),
                    style = MaterialTheme.typography.caption.copy(fontSize = 10.sp),
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }

}

@Composable
@Preview
fun Preview() {
    TwoRowsOfButtonsOffset {

    }
}

@Preview
@Composable
fun IconButtonPreview() {
    IconButton(
        imageVector = Icons.Default.EmojiObjects,
        text = "IDEA"
    ) {

    }
}
