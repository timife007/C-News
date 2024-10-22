package com.timife.c_news.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun formatTimestamp(timestamp: String): String {
    val inputFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
    val outputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy h:mma")

    val zonedDateTime = ZonedDateTime.parse(timestamp, inputFormatter)
    return zonedDateTime.format(outputFormatter)
}