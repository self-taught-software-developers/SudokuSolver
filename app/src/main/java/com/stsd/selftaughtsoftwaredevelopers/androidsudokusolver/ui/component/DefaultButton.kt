package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiObjects
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.cerve.co.material3extension.designsystem.ExtendedTheme.shapes
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.cerve.co.material3extension.designsystem.ExtendedTheme.spacing
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

@Composable
fun ButtonWithNumber(
    num: Int,
    modifier: Modifier = Modifier,
    onButtonClick: (String) -> Unit = { }
) {
    Surface(
        modifier = modifier
            .padding(spacing.small)
            .clip(shape = shapes.small)
            .clickable { onButtonClick(num.toString()) }
            .size(sizes.large),

        color = ExtendedTheme.colors.onSurface.copy(alpha = 0.05F)
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
fun TwoRowsOfButtonsOffset(
    modifier: Modifier = Modifier,
    onButtonClick: (String) -> Unit
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
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
    imageVector: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
//    IconButton(
//        onClick = { onClick() },
//        modifier = modifier
//            .defaultMinSize(sizes.large)
//            .padding(spacing.small)
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Icon(
//                imageVector = imageVector,
//                contentDescription = imageVector.name
//            )
//            Text(
//                text = text.uppercase(),
// //                style = Ex.typography.caption.copy(fontSize = 10.sp), //TODO
//                overflow = TextOverflow.Ellipsis
//            )
//        }
//    }
}

@Composable
fun IconButton(
    backgroundColor: Color,
    imageVector: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    Surface(
        color = backgroundColor,
        shape = shapes.small
    ) {
//        IconButton(
//            onClick = { onClick() },
//            modifier = modifier
//                .defaultMinSize(ExtendedTheme.sizes.large)
//                .padding(spacing.small)
//        ) {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                Icon(
//                    imageVector = imageVector,
//                    contentDescription = imageVector.name
//                )
//                Text(
//                    text = text.uppercase(),
// //                    style = MaterialTheme.typography.caption.copy(fontSize = 10.sp), //TODO
//                    overflow = TextOverflow.Ellipsis
//                )
//            }
//        }
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
