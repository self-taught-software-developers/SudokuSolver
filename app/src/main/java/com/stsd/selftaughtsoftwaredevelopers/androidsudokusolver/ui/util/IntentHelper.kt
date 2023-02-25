package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util

import android.content.Context
import android.content.Intent
import android.os.Build
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.BuildConfig
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.R

const val RECEIVER_EMAIL = "cerve.v55@gmail.com"
const val EMAIL_SUBJECT = "Feature request or Bug report"

fun Context.navigateToEmail(
    receiver: String = RECEIVER_EMAIL,
    subject: String = EMAIL_SUBJECT
) {
    Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_EMAIL, arrayOf(receiver))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(
            Intent.EXTRA_TEXT,
            """  
               Id: ${Build.ID}
               Model: ${Build.MODEL}
               Manufacture: ${Build.MANUFACTURER}
               Android version: ${Build.VERSION.RELEASE}
               App version: ${BuildConfig.VERSION_NAME}
            """.trimIndent()
        )
        type = "message/rfc822"
    }.linkChooser(this, this.getString(R.string.SUBTITLE_email))
}

fun Intent?.linkChooser(context: Context, title: String) {
    if (this != null) {
        try {
            context.startActivity(Intent.createChooser(this, title))
        } catch (_: Exception) { }
    }
}
