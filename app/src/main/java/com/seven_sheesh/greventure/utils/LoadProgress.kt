
package com.seven_sheesh.greventure.utils

import kotlinx.coroutines.delay

suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    val steps = 1000
    for (i in 1..steps) {
        updateProgress(i.toFloat() / steps)
        delay(10)
    }
}