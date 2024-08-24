package com.ahargunyllib.hackathon_kotlin_starter.presentation.ui.design_system

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun Theme(
    content: @Composable () -> Unit,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
) {
    val colorScheme = if (dynamicColor) {
        if (isDarkTheme) darkColorScheme else lightColorScheme
    } else {
        if (isDarkTheme) darkColorScheme else lightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes
    )
}