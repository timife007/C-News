package com.timife.c_news.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatTimestamp(timestamp: String): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        formatUsingDateTimeFormatter(timestamp) // For API 26+
    } else {
        formatUsingSimpleDateFormat(timestamp)  // For older APIs
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun formatUsingDateTimeFormatter(timestamp: String): String {
    val inputFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
    val outputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy h:mma")

    val zonedDateTime = ZonedDateTime.parse(timestamp, inputFormatter)
    return zonedDateTime.format(outputFormatter)
}

private fun formatUsingSimpleDateFormat(timestamp: String): String {
    try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMM dd, yyyy h:mma", Locale.getDefault())

        val date = inputFormat.parse(timestamp)
        return date?.let { outputFormat.format(it) } ?: "Invalid date"
    } catch (e: Exception) {
        e.printStackTrace()
        return "Invalid date"
    }
}