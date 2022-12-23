package com.demo.song_discovery.core

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun String.toFormatDate(): String {
    val date = ZonedDateTime.parse(this)
    val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.getDefault())
    return date.format(formatter)
}
