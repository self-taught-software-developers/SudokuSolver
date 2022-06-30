package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.LocalPadding


@Composable
fun ButtonWithNumber(num: Int, onButtonClick: (String) -> Unit) {

    Surface(
        modifier = Modifier.padding(6.dp), shape = RoundedCornerShape(24.dp)
    ) {
        Button(

            onClick = { onButtonClick(num.toString()) },
            Modifier
                .width(60.dp)
                .height(60.dp)
        ) {
            Text(
                text = num.toString(), textAlign = TextAlign.Center, fontSize = 20.sp

            )
        }
    }
}


@Composable
fun TwoRowsOfButtonsOffset(onButtonClick: (String) -> Unit) {
    val padding = LocalPadding.current
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = padding.massive),
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
@Preview
fun Preview() {
    TwoRowsOfButtonsOffset {

    }
}
