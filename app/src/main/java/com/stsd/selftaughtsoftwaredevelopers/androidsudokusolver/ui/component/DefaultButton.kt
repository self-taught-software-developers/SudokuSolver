package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import android.widget.Toast
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment


@Composable
fun ButtonWithNumber(num: Int, onButtonClick: (String) -> Unit) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier.padding(6.dp),
        shape = RoundedCornerShape(24.dp)
    ) {
        Button(
            onClick = { onButtonClick(num.toString()) },
            Modifier
                .width(60.dp)
                .height(60.dp)
        ) {
            Text(
                text = num.toString(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp

            )
        }
    }
}

@Composable
fun TwoRowsOfButtonsOffset(onButtonClick: (String) -> Unit) {
    FlowRow(mainAxisAlignment = MainAxisAlignment.Center) {
        for (i in 1..9) {
            ButtonWithNumber(num = i){
                onButtonClick(it)
            }
        }
    }
}

@Composable
@Preview
fun Preview() {
    TwoRowsOfButtonsOffset{

    }
}
