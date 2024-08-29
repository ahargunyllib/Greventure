package com.seven_sheesh.greventure.presentation.ui.widget.common

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun DatePicker(
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit,
    onDateChanged: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    LaunchedEffect(Unit) {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                val selectedDate = formatDateTime(calendar)
                onDateSelected(selectedDate)
                onDateChanged(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            setOnDismissListener { onDismiss() }
            show()
        }
    }
}

@Composable
fun TimePicker(
    onTimeSelected: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    LaunchedEffect(Unit) {
        TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                val selectedTime = formatDateTime(calendar)
                onTimeSelected(selectedTime)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).apply {
            setOnDismissListener { onDismiss() }
            show()
        }
    }
}

@Composable
fun TimePicker2(
    onTimeSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val hour = 0
    val minute = 0

    LaunchedEffect(Unit) {
        android.app.TimePickerDialog(
            context,
            { _, selectedHour, selectedMinute ->
                calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
                calendar.set(Calendar.MINUTE, selectedMinute)
                calendar.set(Calendar.SECOND, 0) // Ensure seconds are set to 00

                val format = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
                val selectedTime = format.format(calendar.time)

                // Call the callback with the formatted time
                onTimeSelected(selectedTime)
            },
            hour, minute, true
        ).apply {
            setOnDismissListener { onDismiss() }
            show()
        }
    }
}



private fun formatDateTime(calendar: Calendar): String {
    val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ssXXX", Locale.getDefault())
    return dateTimeFormat.format(calendar.time)
}
