package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.FUNCTION
)
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Repeatable
annotation class DarkPreview()