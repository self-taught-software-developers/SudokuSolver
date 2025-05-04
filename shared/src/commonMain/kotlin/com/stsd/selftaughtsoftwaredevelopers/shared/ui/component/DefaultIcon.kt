package com.stsd.selftaughtsoftwaredevelopers.shared.ui.component

//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.Icon
//import androidx.compose.material.Surface
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
//import com.cerve.co.material3extension.designsystem.ExtendedTheme.spacing
//import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.icon.Grid2x2
//
//@Composable
//fun IconTitle(
//    icon: ImageVector,
//    text: String,
//    modifier: Modifier = Modifier,
//    onClick: () -> Unit = { }
//) {
//    Column(
//        modifier = modifier,
//        verticalArrangement = Arrangement.spacedBy(spacing.medium),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Surface(
//            modifier = Modifier
//                .clip(CircleShape)
//                .clickable { onClick() },
//            color = Color.Black.copy(alpha = 0.1F)
//        ) {
//            Icon(
//                modifier = Modifier
//                    .padding(spacing.medium)
//                    .size(sizes.xSmall),
//                imageVector = icon,
//                contentDescription = icon.name
//            )
//        }
//
//        Text(
//            modifier = Modifier.padding(spacing.medium),
//            text = text,
//            textAlign = TextAlign.Center
//        )
//    }
//}
//
//@Preview
//@Composable
//fun IconTitlePreview() {
//    IconTitle(
//        icon = Grid2x2,
//        text = Grid2x2.name
//    ) {
//    }
//}
