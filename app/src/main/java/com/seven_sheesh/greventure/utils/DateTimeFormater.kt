package com.seven_sheesh.greventure.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun formatDateTime(dateTimeString: String): String {
    val inputFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME

    return try {
        val zonedDateTime = ZonedDateTime.parse(dateTimeString, inputFormatter)
        val outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm:ss a")
        zonedDateTime.format(outputFormatter)
    } catch (e: DateTimeParseException) {
        println("DateTimeParseException: ${e.message}")
        "Invalid date/time format"
    } catch (e: Exception) {
        println("Exception: ${e.message}")
        "Error formatting date/time"
    }
}